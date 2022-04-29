package app.hdj.datepick.domain.model.auth

sealed class RefreshTokenResult {

    object Refreshed : RefreshTokenResult()
    object NoNeedToRefresh : RefreshTokenResult()
    object NotSigned : RefreshTokenResult()

}