package Network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(engine: HttpClientEngine): HttpClient = HttpClient(engine) {
    install(Logging) {
        this.level = LogLevel.ALL
        this.logger = Logger.SIMPLE
    }

    install(ContentNegotiation){
        this.json(
            json = Json {
                ignoreUnknownKeys = true
            }
        )
    }
}