package uz.dkamaloff.githubkmm.githubSdk

import uz.dkamaloff.githubkmm.githubSdk.entities.OAuthParams

actual fun SDK(): GithubSDK = GithubSDK(
    OAuthParams(
        clientId = BuildKonfig.clientId,
        redirectUri = BuildKonfig.redirectUri,
        scope = BuildKonfig.scope,
        state = BuildKonfig.state
    )
)