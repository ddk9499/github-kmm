package uz.dkamaloff.githubkmm.githubSdk.model.interactor

import io.ktor.http.*
import uz.dkamaloff.githubkmm.githubSdk.entities.AccessTokenRequest
import uz.dkamaloff.githubkmm.githubSdk.entities.AccessTokenResponse
import uz.dkamaloff.githubkmm.githubSdk.entities.OAuthParams
import uz.dkamaloff.githubkmm.githubSdk.model.data.server.LoginService

class LoginInteractor internal constructor(
    private val loginService: LoginService,
    private val oAuthParams: OAuthParams,
) {

    suspend fun login(redirectedUri: String): AccessTokenResponse =
        loginService.loadTokenData(
            AccessTokenRequest(
                clientId = oAuthParams.clientId,
                clientSecret = oAuthParams.clientSecret,
                code = getQueryParameterFromUri(redirectedUri, "code")
            )
        )

    private fun getQueryParameterFromUri(url: String, queryName: String) =
        Url(url).parameters[queryName] ?: ""
}