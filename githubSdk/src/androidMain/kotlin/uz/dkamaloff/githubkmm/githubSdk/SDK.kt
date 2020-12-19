package uz.dkamaloff.githubkmm.githubSdk

import io.ktor.client.engine.okhttp.*
import uz.dkamaloff.githubkmm.githubSdk.entities.OAuthParams
import uz.dkamaloff.githubkmm.githubSdk.util.HttpClientFactory
import java.util.concurrent.TimeUnit

fun GithubSDK.Companion.create() = GithubSDK(
    oAuthParams = OAuthParams(
        clientId = BuildKonfig.clientId,
        clientSecret = BuildKonfig.clientSecret,
        redirectUri = BuildKonfig.redirectUri,
        scope = BuildKonfig.scope,
        state = BuildKonfig.state
    ),
    isDebug = BuildConfig.DEBUG,
    httpClientFactory = HttpClientFactory { _, timeout ->
        OkHttp.create {
            config {
                followRedirects(true)
                followSslRedirects(true)
                retryOnConnectionFailure(true)
                connectTimeout(timeout, TimeUnit.SECONDS)
                readTimeout(timeout, TimeUnit.SECONDS)
            }
        }
    },
)