package app.hdj.datepick.domain.usecase.user


data class UserProfileRequestParams(
    val nickname: String? = null,
    val profileImageUrl: String? = null,
    val gender: Gender? = null
) {
    enum class Gender(val value: String) {
        Male("M"),
        Female("F")
    }
}
