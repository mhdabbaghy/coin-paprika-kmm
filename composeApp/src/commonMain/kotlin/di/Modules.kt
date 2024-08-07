package di

import Repository.CoinRepository
import Repository.CoinRepositoryImpl
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import coin_list.CoinListViewModel
import Network.CoinPaprikaClient

expect val platformModule: Module

val sharedModule = module {
    singleOf(::CoinRepositoryImpl).bind<CoinRepository>()
    singleOf(::CoinPaprikaClient)
    viewModelOf(::CoinListViewModel)
}