package di

import data.repository.CoinRepository
import data.repository.CoinRepositoryImpl
import feature.coin_detail.di.coinDetailModule
import feature.coin_list.di.coinListModule
import feature.home.di.homeModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(::CoinRepositoryImpl).bind<CoinRepository>()
}

val featureModules = module {
    includes(
        coinDetailModule,
        coinListModule,
        homeModule,
    )
}