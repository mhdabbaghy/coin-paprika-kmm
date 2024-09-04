package data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("description")
    val description: String,
    @SerialName("logo")
    val logo: String,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("development_status")
    val developmentStatus: String,
    @SerialName("started_at")
    val startedAt: String? = null,
    @SerialName("team")
    val team: List<Team>,
    @SerialName("type")
    val type: String
) {
    @Serializable
    data class Team(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("position")
        val position: String,
    )
}