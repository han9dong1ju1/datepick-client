package app.hdj.datepick.data.model.response.district

@kotlinx.serialization.Serializable
data class DistrictResponse(
    val id : Long,
    val name : String,
    val latitude : Double,
    val longitude : Double
)