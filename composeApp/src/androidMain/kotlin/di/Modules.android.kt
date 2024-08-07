package di

import Network.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single {
        createHttpClient(OkHttp.create())
    }
}