package coin_list

import Repository.CoinRepository
import Util.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val repository: CoinRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CoinListUiState>(CoinListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchCoins()
    }

    private fun fetchCoins() {
        viewModelScope.launch {
            _uiState.value = when (val result = repository.getCoins()) {
                is Result.Success -> CoinListUiState.Success(result.data)
                is Result.Error -> CoinListUiState.Error(result.throwable)
            }
        }
    }

}