package uz.dkamaloff.githubkmm.githubSdk

import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier
import io.ktor.client.engine.ios.*
import uz.dkamaloff.githubkmm.githubSdk.entities.OAuthParams
import uz.dkamaloff.githubkmm.githubSdk.util.HttpClientFactory

actual fun SDK(): GithubSDK = GithubSDK(
    oAuthParams = OAuthParams(
        clientId = BuildKonfig.clientId,
        clientSecret = BuildKonfig.clientSecret,
        redirectUri = BuildKonfig.redirectUri,
        scope = BuildKonfig.scope,
        state = BuildKonfig.state
    ),
    // TODO change this, when reimplement SDK create.
    isDebug = true,
    httpClientFactory = HttpClientFactory { _, _ -> Ios.create() }
)

fun debugBuild() {
    Napier.base(DebugAntilog())
}