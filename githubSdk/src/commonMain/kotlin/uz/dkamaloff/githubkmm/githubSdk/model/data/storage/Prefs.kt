package uz.dkamaloff.githubkmm.githubSdk.model.data.storage

import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json
import uz.dkamaloff.githubkmm.githubSdk.entities.AccessTokenResponse

private const val KEY_TOKEN_DATA = "token_data"

internal class Prefs(
    private val settings: Settings,
    private val json: Json,
) {

    var tokenData: AccessTokenResponse
        get() = json.decodeFromString(
            AccessTokenResponse.serializer(), settings.getString(
                KEY_TOKEN_DATA, ""
            )
        )
        set(value) {
            settings.putString(
                KEY_TOKEN_DATA,
                json.encodeToString(AccessTokenResponse.serializer(), value)
            )
        }
}