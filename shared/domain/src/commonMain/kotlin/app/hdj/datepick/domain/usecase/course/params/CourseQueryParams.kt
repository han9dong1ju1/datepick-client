package app.hdj.datepick.domain.usecase.course.params

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course

@kotlinx.serialization.Serializable
data class CourseQueryParams(
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
            Popular("popular")
        }
    }

    @kotlinx.serialization.Serializable
    data class FilterParams(
        var keyword: String? = null,
        var tagIds: List<Int>? = null,
        var featuredId: Long? = null,
        var placeId: Long? = null,
        var userId: Long? = null
    )


}

fun courseQueryParams(
    block: CourseQueryParams.() -> Unit
) = CourseQueryParams().apply(block)

fun CourseQueryParams.pagingParams(
    block: CourseQueryParams.PagingParams.() -> Unit
) {
    pagingParams = CourseQueryParams.PagingParams().apply(block)
}

fun CourseQueryParams.filterParams(
    block: CourseQueryParams.FilterParams.() -> Unit
) {
    filterParams = CourseQueryParams.FilterParams().apply(block)
}

data class CourseQueryResult(
    val queryParams: CourseQueryParams = CourseQueryParams(),
    val result: LoadState<List<Course>> = LoadState.idle()
)