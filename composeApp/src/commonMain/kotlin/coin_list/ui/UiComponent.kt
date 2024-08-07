package coin_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import network.response.CoinListResponse
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoinItem(
    coin: CoinListResponse,
    onCoinClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        onClick = {
            onCoinClick(coin.id)
        },
        modifier = modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${coin.rank}. ${coin.name} (${coin.symbol})"
            )

            Box(
                modifier = Modifier.size(8.dp)
                    .background(
                        color = if (coin.isActive) Color.Green else Color.Red,
                        shape = CircleShape
                    )
            )

        }

    }

}

@Preview
@Composable
fun CoinItemPreview() {
    MaterialTheme {
        Surface {
            CoinItem(
                coin = CoinListResponse(
                    id = "BTC-Bitcoin",
                    isActive = true,
                    isNew = true,
                    name = "Bitcoin",
                    rank = 1,
                    symbol = "BTC",
                    type = "Coin"
                ),
                onCoinClick = {}
            )
        }
    }
}