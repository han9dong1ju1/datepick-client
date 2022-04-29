package app.hdj.datepick.domain.usecase.auth.params

data class SignInRequest(
    val code : String,
    val provider : Provider
) {

    enum class Provider(val value: String) {
        GOOGLE("google"),
        KAKAO("kakao")
    }
}