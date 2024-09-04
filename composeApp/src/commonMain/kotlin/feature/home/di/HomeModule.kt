package feature.home.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import feature.home.HomeViewModel
import feature.home.domain.GetGlobalDataUseCase
import org.koin.core.module.dsl.singleOf

val homeModule = module {
    viewModelOf(::HomeViewModel)
    singleOf(::GetGlobalDataUseCase)
}