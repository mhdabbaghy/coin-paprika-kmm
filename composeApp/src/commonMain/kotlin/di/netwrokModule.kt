package di

import io.ktor.client.engine.HttpClientEngine
import data.network.createHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import data.network.CoinPaprikaClient


val networkModule = module {
    single {
        platformEngine
    }.bind(HttpClientEngine::class)

    singleOf(::createHttpClient)
    singleOf(::CoinPaprikaClient)
}

expect val platformEngine: HttpClientEngine
