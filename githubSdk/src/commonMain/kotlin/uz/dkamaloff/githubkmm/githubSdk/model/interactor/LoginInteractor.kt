package uz.dkamaloff.githubkmm.githubSdk.model.interactor

import io.ktor.http.*
import uz.dkamaloff.githubkmm.githubSdk.entities.AccessTokenRequest
import uz.dkamaloff.githubkmm.githubSdk.entities.OAuthParams
import uz.dkamaloff.githubkmm.githubSdk.model.data.server.LoginService
import uz.dkamaloff.githubkmm.githubSdk.model.data.storage.Prefs

class LoginInteractor internal constructor(
    private val loginService: LoginService,
    private val oAuthParams: OAuthParams,
    private val prefs: Prefs,
) {

    suspend fun login(redirectedUri: String) {
        val tokenData = loginService.loadTokenData(
            AccessTokenRequest(
                clientId = oAuthParams.clientId,
                clientSecret = oAuthParams.clientSecret,
                code = getQueryParameterFromUri(redirectedUri, "code")
            )
        )
        prefs.tokenData = tokenData
    }

    private fun getQueryParameterFromUri(url: String, queryName: String) =
        Url(url).parameters[queryName] ?: ""
}