package app.hdj.datepick.domain.usecase.place.params

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place

@kotlinx.serialization.Serializable
data class PlaceQueryParams(
    var pagingParams: PagingParams = PagingParams(),
    var filterParams: FilterParams = FilterParams()
) {

    @kotlinx.serialization.Serializable
    data class PagingParams(
        var sort: Sort = Sort.Latest
    ) {
        enum class Sort(val value: String) {
            Latest("latest"),
            Pick("pick"),
            Popular("popular"),
            RatingDesc("rating_desc"),
            RatingAsc("rating_asc"),
            DistanceDesc("distance_desc"),
            DistanceAsc("distance_asc"),
        }
    }

    @kotlinx.serialization.Serializable
    data class FilterParams(
        var keyword: String? = null,
        var categoryIds: List<Long>? = null,
        var latitude: Double? = null,
        var longitude: Double? = null,
        var distance: Double? = null,
        var courseId: Long? = null
    ) {

        fun nearby(latitude: Double, longitude: Double, distance: Double) {
            this.latitude = latitude
            this.longitude = longitude
            this.distance = distance
        }

    }

}

fun placeQueryParams(block: PlaceQueryParams.() -> Unit) = PlaceQueryParams().apply(block)

fun PlaceQueryParams.pagingParams(block: PlaceQueryParams.PagingParams.() -> Unit) {
    pagingParams = PlaceQueryParams.PagingParams().apply(block)
}

fun PlaceQueryParams.filterParams(block: PlaceQueryParams.FilterParams.() -> Unit) {
    filterParams = PlaceQueryParams.FilterParams().apply(block)
}

data class PlaceQueryWithResult(
    val queryParams: PlaceQueryParams = PlaceQueryParams(),
    val result: LoadState<List<Place>> = LoadState.idle()
)