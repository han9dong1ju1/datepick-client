package app.hdj.datepick.data.model.request.course

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseCommitRequest(
    @SerialName("course") val courseInfo: CourseInfo,
    @SerialName("places") val places: List<PlaceRelation> = emptyList()
) {

    companion object {
        fun requestForCreate(
            title : String,
            region: String
        ) = CourseCommitRequest(
            CourseInfo(
                title = title,
                region = region
            )
        )

        fun requestForUpdate(
            id : Long,
            title : String,
            region: String,
            places: List<PlaceRelation>
        ) = CourseCommitRequest(
            CourseInfo(
                id = id,
                title = title,
                region = region
            ),
            places
        )

    }

    @Serializable
    data class CourseInfo(
        @SerialName("id") val id: Long? = null,
        @SerialName("region") val region: String,
        @SerialName("thumb_nail_url") val thumbnailUrl: String? = null,
        @SerialName("title") val title: String,
        @SerialName("expected_at") val expectedAt: String? = null,
    )

    @Serializable
    data class PlaceRelation(
        @SerialName("memo") val memo: String,
        @SerialName("place_id") val placeId: Int,
        @SerialName("place_order") val placeOrder: Int,
        @SerialName("visit_time") val visitTime: String
    )
}