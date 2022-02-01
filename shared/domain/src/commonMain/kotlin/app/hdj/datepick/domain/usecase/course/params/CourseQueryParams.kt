package app.hdj.datepick.domain.usecase.course.params

data class CourseQueryParams(
    val pagingParams: PagingParams = PagingParams(),
    val filterParams: FilterParams = FilterParams()
) {

    companion object {

    }

    data class PagingParams(val sort : Sort = Sort.Latest) {
        enum class Sort(val value : String) {
            Latest("latest"),
            Pick("pick"),
            Popular("popular")
        }
    }

    data class FilterParams(
        val keyword : String? = null,
        val tags : List<String>? = null,
        val featuredId : Long? = null,
        val placeId : Long? = null,
        val userId : Long? = null
    )


}