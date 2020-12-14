package uz.dkamaloff.githubkmm.githubSdk.entities

data class OAuthParams(
    val clientId: String,
    val redirectUri: String,
    val scope: String,
    val state: String
)