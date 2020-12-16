package uz.dkamaloff.githubkmm.githubSdk

import kotlinx.serialization.json.Json
import uz.dkamaloff.githubkmm.githubSdk.entities.OAuthParams
import uz.dkamaloff.githubkmm.githubSdk.model.data.server.LoginService
import uz.dkamaloff.githubkmm.githubSdk.model.interactor.LoginInteractor
import uz.dkamaloff.githubkmm.githubSdk.util.HttpClientFactory

class GithubSDK internal constructor(
    val oAuthParams: OAuthParams,
    httpClientFactory: HttpClientFactory,
    isDebug: Boolean,
) {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }

    private val loginService =
        LoginService("https://github.com/login/oauth", httpClientFactory.create(json, isDebug))

    fun getLoginInteractor() = LoginInteractor(loginService, oAuthParams)

}