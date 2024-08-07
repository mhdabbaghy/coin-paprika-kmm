package coin_detail.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import network.response.CoinDetailResponse
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetail(
    coin: CoinDetailResponse,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {

        Row(
            modifier = Modifier.height(96.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            ) {

                AsyncImage(
                    model = coin.logo,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                        .background(
                            color = if (coin.isActive) Color.Green else Color.Red,
                            shape = CircleShape
                        )
                        .size(16.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {

                Text(text = "Name: ${coin.name}")
                Text(text = "Symbol: ${coin.symbol}")
                Text(text = "Development: ${coin.developmentStatus}")

            }

        }

        Text(
            text = "Type: ${coin.type}",
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Description: ",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = coin.description,
            modifier = Modifier.padding(top = 8.dp)
        )

        FlowRow(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            for (team in coin.team) {
                Card {
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(text = team.position)
                        Text(
                            text = team.name,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                    }
                }

            }


        }


    }

}

@Preview
@Composable
fun PreviewCoinDetail() {

    MaterialTheme {
        Surface {
            CoinDetail(
                coin = CoinDetailResponse(
                    id = "BTC-bitcoin",
                    name = "Bitcoin",
                    symbol = "BTC",
                    description = "Description".repeat(20),
                    logo = "",
                    isActive = true,
                    developmentStatus = "WIP",
                    startedAt = "2009-01-03T00:00:00Z",
                    team = List(5) {
                        CoinDetailResponse.Team(
                            id = "$it", name = "Name $it", position = "Position $it"
                        )
                    },
                    type = "Coin"
                )
            )
        }
    }

}