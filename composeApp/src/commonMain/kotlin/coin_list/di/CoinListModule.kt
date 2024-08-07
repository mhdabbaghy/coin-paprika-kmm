package coin_list.di

import coin_list.CoinListViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val coinListModule = module {
    viewModelOf(::CoinListViewModel)
}