package coin_detail

import androidx.compose.foundation.background
import network.response.CoinDetailResponse
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coin_detail.ui_components.CoinDetail
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CoinDetailRoute(
    onBackClick: () -> Unit,
    id: String,
    viewModel: CoinDetailViewModel = koinViewModel { parametersOf(id) }
) {

    val state by viewModel.uiState.collectAsState()
    CoinDetailScreen(
        state = state, onBackClick = onBackClick
    )

}

@Composable
fun CoinDetailScreen(
    state: CoinDetailUiState,
    onBackClick: () -> Unit,
) {

    Scaffold(topBar = {
        TopAppBar(title = {
            val title =
                if (state is CoinDetailUiState.Success) state.data.id else "Coin detail"
            Text(text = title)
        }, navigationIcon = {
            IconButton(
                onClick = onBackClick,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        })
    }) {

        when (state) {
            CoinDetailUiState.Loading -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center,
            ) { CircularProgressIndicator() }

            is CoinDetailUiState.Success -> CoinDetail(state.data, modifier = Modifier.padding(it))
            is CoinDetailUiState.Error -> throw state.throwable
        }

    }

}