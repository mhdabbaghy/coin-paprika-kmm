package feature.coin_list

import data.network.response.CoinListResponse

sealed interface CoinListUiState {
    data object Loading : CoinListUiState
    data class Success(val coins: List<CoinListResponse>) : CoinListUiState
    data class Error(val throwable: Throwable) : CoinListUiState
}