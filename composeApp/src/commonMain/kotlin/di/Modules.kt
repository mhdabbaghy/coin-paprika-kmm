package di

import repository.CoinRepository
import repository.CoinRepositoryImpl
import coin_detail.di.coinDetailModule
import coin_list.di.coinListModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import network.CoinPaprikaClient
import network.createHttpClient

expect val platformModule: Module

val sharedModule = module {
    singleOf(::createHttpClient)
    singleOf(::CoinPaprikaClient)
    singleOf(::CoinRepositoryImpl).bind<CoinRepository>()
}

val featureModules = module {
    includes(
        coinDetailModule,
        coinListModule,
    )
}