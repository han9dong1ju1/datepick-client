package app.hdj.datepick.domain.model

data class Review(
    val id : String,
    val text : String,
    val photos : List<String>,
    val author : User
)