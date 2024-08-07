package network

import network.response.CoinDetailResponse
import network.response.CoinListResponse
import util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CoinPaprikaClient(
    private val httpClient: HttpClient,
) {

    suspend fun getAllCoins(): Result<List<CoinListResponse>> {
        return try {
            val response = httpClient.get(
                urlString = "https://api.coinpaprika.com/v1/coins"
            )
            val list = response.body<List<CoinListResponse>>()
            Result.Success(list)
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }

    suspend fun getCoinById(id: String): Result<CoinDetailResponse> {
        return try {
            val response = httpClient.get(
                urlString = "https://api.coinpaprika.com/v1/coins/$id"
            )
            val detail = response.body<CoinDetailResponse>()
            Result.Success(detail)
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }

}