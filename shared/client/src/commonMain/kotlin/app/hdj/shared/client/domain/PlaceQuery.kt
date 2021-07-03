package app.hdj.shared.client.domain

data class PlaceQuery(
    val search: String? = null,
    val sort: Sort = Sort.RELATIVE
) {

    enum class Sort(val value: String) {
        RELATIVE("relative"),
        POPULAR("popular"),
        RATING("rating"),
        DISTANCE_FROM_LAST_POSITION("distance_from_last_position"),
    }

}