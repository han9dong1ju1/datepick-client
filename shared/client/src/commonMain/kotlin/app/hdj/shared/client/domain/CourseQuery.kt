package app.hdj.shared.client.domain

data class CourseQuery(
    val search: String? = null,
    val sort: Sort = Sort.RELATIVE
) {

    enum class Sort(val value: String) {
        RELATIVE("relative"),
        POPULAR("popular"),
    }

}