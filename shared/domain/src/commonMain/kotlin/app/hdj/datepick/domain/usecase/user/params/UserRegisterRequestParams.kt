package app.hdj.datepick.domain.usecase.user.params

data class UserRegisterRequestParams(
    val provider : String,
    val token : String
)