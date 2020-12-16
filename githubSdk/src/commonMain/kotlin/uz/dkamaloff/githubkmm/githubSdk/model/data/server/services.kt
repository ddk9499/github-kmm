package uz.dkamaloff.githubkmm.githubSdk.model.data.server

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import uz.dkamaloff.githubkmm.githubSdk.entities.AccessTokenRequest
import uz.dkamaloff.githubkmm.githubSdk.entities.AccessTokenResponse

internal class LoginService(
    private val endpoint: String,
    private val httpClient: HttpClient,
) {

    suspend fun loadTokenData(requestParams: AccessTokenRequest): AccessTokenResponse =
        httpClient.post("$endpoint/access_token") {
            contentType(ContentType.Application.Json)
            body = requestParams
        }
}