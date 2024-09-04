package feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.home.domain.GetGlobalDataUseCase
import feature.home.domain.GlobalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import util.Result

class HomeViewModel(
    getGlobalDataUseCase: GetGlobalDataUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getGlobalDataUseCase()
            .onEach { result ->
                _uiState.update { currentState ->
                    when (result) {
                        is Result.Success -> HomeUiState.Success(globalData = result.data)
                        is Result.Error -> HomeUiState.Error(
                            message = result.throwable.message!!,
                            blocking = currentState is HomeUiState.Loading
                        )
                    }
                }

            }.launchIn(viewModelScope)
    }

}

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val globalData: GlobalData) : HomeUiState
    data class Error(val message: String, val blocking: Boolean) : HomeUiState
}