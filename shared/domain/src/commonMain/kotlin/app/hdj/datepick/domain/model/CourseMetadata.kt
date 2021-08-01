package app.hdj.datepick.domain.model

data class CourseMetadata(
    val id: String,
    val name: String,
    val options: Options,
    val isPicked : Boolean,
    val author : User
) {

    data class Options(
        val dining : Dining,
        val hasCar : Boolean,
        val drink : Boolean,
        val price : Price
    ) {

        enum class Price {
            LOW, MEDIUM, HIGH
        }

        enum class Dining {
            LUNCH, DINNER, LUNCH_DINNER, NO
        }

    }

}