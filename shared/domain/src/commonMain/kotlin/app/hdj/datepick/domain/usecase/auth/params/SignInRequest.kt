package app.hdj.datepick.domain.usecase.auth.params

data class SignInRequest(
    val token : String,
    val provider : Provider
) {

    enum class Provider(val value: String) {
        GOOGLE("google"),
        KAKAO("kakao")
    }
}