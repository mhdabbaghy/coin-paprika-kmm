package feature.home.domain

data class GlobalData(
    val bitcoinDominancePercentage: Double,
    val cryptocurrenciesNumber: Int,
    val marketCapChange24h: Double,
    val marketCapUsd: Long,
    val volume24hChange24h: Double,
    val volume24hUsd: Long,
    val lastUpdated: Int,
)
