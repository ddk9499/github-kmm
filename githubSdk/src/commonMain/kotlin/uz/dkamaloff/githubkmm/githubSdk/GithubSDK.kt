package uz.dkamaloff.githubkmm.githubSdk

import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json
import uz.dkamaloff.githubkmm.githubSdk.entities.OAuthParams
import uz.dkamaloff.githubkmm.githubSdk.model.data.server.LoginService
import uz.dkamaloff.githubkmm.githubSdk.model.data.storage.Prefs
import uz.dkamaloff.githubkmm.githubSdk.model.interactor.LoginInteractor
import uz.dkamaloff.githubkmm.githubSdk.util.HttpClientFactory

class GithubSDK internal constructor(
    val oAuthParams: OAuthParams,
    httpClientFactory: HttpClientFactory,
    isDebug: Boolean,
    private val settings: Settings
) {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }

    private val loginService =
        LoginService("https://github.com/login/oauth", httpClientFactory.create(json, isDebug))

    fun getLoginInteractor() = LoginInteractor(loginService, oAuthParams, Prefs(settings, json))

    /**
     * We need this to create instance of [GithubSDK] via extension functions
     * from platform specific modules.
     */
    companion object
}