package data.repository

import data.network.CoinPaprikaClient
import data.network.response.CoinDetailResponse
import data.network.response.CoinListResponse
import data.network.response.GlobalResponse
import util.Result

interface CoinRepository {

    suspend fun getGlobal(): GlobalResponse

    suspend fun getCoins(): Result<List<CoinListResponse>>

    suspend fun getCoinById(id: String): Result<CoinDetailResponse>
}

class CoinRepositoryImpl(
    private val client: CoinPaprikaClient
) : CoinRepository {

    override suspend fun getGlobal(): GlobalResponse {
        return client.getGlobal()
    }

    override suspend fun getCoins(): Result<List<CoinListResponse>> {
        return client.getAllCoins()
    }

    override suspend fun getCoinById(id: String): Result<CoinDetailResponse> {
        return client.getCoinById(id)
    }

}