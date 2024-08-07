package Repository

import Network.CoinPaprikaClient
import network.response.CoinDetailResponse
import network.response.CoinListResponse
import Util.Result

interface CoinRepository {

    suspend fun getCoins(): Result<List<CoinListResponse>>

    suspend fun getCoinById(id: String): Result<CoinDetailResponse>
}

class CoinRepositoryImpl(
    private val client: CoinPaprikaClient
) : CoinRepository {
    override suspend fun getCoins(): Result<List<CoinListResponse>> {
        return client.getAllCoins()
    }

    override suspend fun getCoinById(id: String): Result<CoinDetailResponse> {
        return client.getCoinById(id)
    }

}