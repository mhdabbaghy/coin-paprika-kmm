package data.network

import data.network.response.CoinDetailResponse
import data.network.response.CoinListResponse
import data.network.response.GlobalResponse
import util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

private const val BASE_URL = "https://api.coinpaprika.com/v1/"

class CoinPaprikaClient(
    private val httpClient: HttpClient,
) {

    suspend fun getGlobal(): GlobalResponse {
        val response = httpClient.get("${BASE_URL}global")
        return when(response.status.value){
            200 -> response.body()
            429 -> throw Exception("Too many requests")
            else -> throw RuntimeException()
        }
    }

    suspend fun getAllCoins(): Result<List<CoinListResponse>> {
        return try {
            val response = httpClient.get(
                urlString = "${BASE_URL}coins"
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
                urlString = "${BASE_URL}coins/$id"
            )
            val detail = response.body<CoinDetailResponse>()
            Result.Success(detail)
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }

}