package feature.coin_list.di

import feature.coin_list.CoinListViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val coinListModule = module {
    viewModelOf(::CoinListViewModel)
}