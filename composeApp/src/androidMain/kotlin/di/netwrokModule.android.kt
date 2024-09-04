package di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual val platformEngine: HttpClientEngine = OkHttp.create()