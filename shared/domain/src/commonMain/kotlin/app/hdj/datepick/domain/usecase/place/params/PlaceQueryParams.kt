package app.hdj.datepick.domain.usecase.place.params

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import com.kuuurt.paging.multiplatform.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

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
            Distance("distance")
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

        fun nearby(latitude: Double, longitude: Double, distance: Double? = null) {
            this.latitude = latitude
            this.longitude = longitude
            this.distance = distance ?: 1.0
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
    var queryParams: PlaceQueryParams = PlaceQueryParams(),
    val result: LoadState<List<Place>> = LoadState.idle()
)

data class PlaceQueryWithPagingResult(
    var queryParams: PlaceQueryParams = PlaceQueryParams(),
    val result: Flow<PagingData<Place>> = flowOf()
)