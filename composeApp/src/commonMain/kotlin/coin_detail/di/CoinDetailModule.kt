package coin_detail.di

import coin_detail.CoinDetailViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coinDetailModule = module {
    viewModel {
        CoinDetailViewModel(id = it.get<String>(), repository = get())
    }
}