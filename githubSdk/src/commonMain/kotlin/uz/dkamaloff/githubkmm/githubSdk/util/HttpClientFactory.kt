package uz.dkamaloff.githubkmm.githubSdk.util

import com.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

internal class HttpClientFactory(
    private val engineFactory: (cacheSize: Long, timeout: Long) -> HttpClientEngine
) {
    fun create(
        json: Json,
        enableLogging: Boolean,
    ): HttpClient = HttpClient(engineFactory(CACHE_SIZE_BYTES, TIMEOUT)) {
        if (enableLogging) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "HttpClient", message = message)
                    }
                }
            }
        }
        install(JsonFeature) {
            acceptContentTypes = listOf(ContentType.Application.Json)
            serializer = KotlinxSerializer(json)
        }
    }

    companion object {
        private const val CACHE_SIZE_BYTES = 20 * 1024L
        private const val TIMEOUT = 30L
    }
}