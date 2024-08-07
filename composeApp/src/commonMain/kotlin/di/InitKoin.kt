package di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import coinDetailKoinModule

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)

        modules(
            sharedModule,
            platformModule,
            coinDetailKoinModule,
        )
    }
}