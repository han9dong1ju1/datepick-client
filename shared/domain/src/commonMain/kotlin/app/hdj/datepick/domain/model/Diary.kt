package app.hdj.datepick.domain.model

data class Diary(
    val id : String,
    val title : String,
    val coverPhoto : String,
    val author : User
)