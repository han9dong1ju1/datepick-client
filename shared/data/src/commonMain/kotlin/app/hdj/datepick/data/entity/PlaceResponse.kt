package app.hdj.datepick.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponse(
    val id : String,
    val name : String
)