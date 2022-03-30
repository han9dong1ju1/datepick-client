package app.hdj.datepick.android.data


@kotlinx.serialization.Serializable
data class CityData(
    val district: String,
    val regions: List<Region>
) {

    @kotlinx.serialization.Serializable
    data class Region(
        val code: Int,
        val name: String,
        val lat: Double,
        val lng: Double
    )

}