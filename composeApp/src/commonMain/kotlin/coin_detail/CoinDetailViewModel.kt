package coin_detail

import network.response.CoinDetailResponse
import repository.CoinRepository
import util.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinDetailViewModel(
    private val id: String,
    private val repository: CoinRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CoinDetailUiState>(CoinDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchCoin()
    }

    private fun fetchCoin() {
        viewModelScope.launch {
            _uiState.value = when (val result = repository.getCoinById(id)) {
                is Result.Success -> CoinDetailUiState.Success(result.data)
                is Result.Error -> CoinDetailUiState.Error(result.throwable)
            }
        }
    }

}

sealed interface CoinDetailUiState {
    data object Loading : CoinDetailUiState
    data class Success(val data: CoinDetailResponse) : CoinDetailUiState
    data class Error(val throwable: Throwable) : CoinDetailUiState
}
