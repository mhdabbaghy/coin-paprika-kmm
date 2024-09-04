package feature.home.domain

import data.repository.CoinRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import util.Result
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class GetGlobalDataUseCase(
    private val repository: CoinRepository,
) {

    operator fun invoke() = flow<Result<GlobalData>> {
        while (true) {
            val response = repository.getGlobal()
            val state = GlobalData(
                bitcoinDominancePercentage = response.bitcoinDominancePercentage,
                cryptocurrenciesNumber = response.cryptocurrenciesNumber,
                marketCapChange24h = response.marketCapChange24h,
                marketCapUsd = response.marketCapUsd,
                volume24hChange24h = response.volume24hChange24h,
                volume24hUsd = response.volume24hUsd,
                lastUpdated = response.lastUpdated,
            )
            emit(Result.Success(state))
            delay(5.toDuration(unit = DurationUnit.MINUTES))
        }
    }

}