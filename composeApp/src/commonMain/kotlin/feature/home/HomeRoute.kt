package feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    HomeScreen(
        state = state,
    )
}

@Composable
fun HomeScreen(state: HomeUiState) {

    when (state) {
        is HomeUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }

        is HomeUiState.Success -> {
            Text(text = state.globalData.toString())
        }

        is HomeUiState.Error -> {
            Text(state.message)
        }
    }

}