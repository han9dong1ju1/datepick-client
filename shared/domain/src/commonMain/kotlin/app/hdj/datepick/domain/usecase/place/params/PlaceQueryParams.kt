package app.hdj.datepick.domain.usecase.place.params

data class PlaceQueryParams(
    val pagingParams: PagingParams = PagingParams(),
    val filterParams: FilterParams = FilterParams()
) {

    data class PagingParams(val sort: Sort = Sort.Latest) {
        enum class Sort(val value : String) {
            Latest("latest"),
            Pick("pick"),
            Popular("popular"),
            RatingDesc("rating_desc"),
            RatingAsc("rating_asc"),
            DistanceDesc("distance_desc"),
            DistanceAsc("distance_asc"),
        }
    }

    data class FilterParams(
        val keyword : String? = null,
        val category : String? = null,
        val latitude : Double? = null,
        val longitude : Double? = null,
        val distance : Double? = null,
        val courseId : Long? = null
    ) {

        companion object {

            fun withGeopoints(latitude: Double, longitude: Double) = FilterParams(
                latitude = latitude,
                longitude = longitude
            )

        }

    }

}