package app.hdj.datepick.data.utils

import app.hdj.datepick.data.model.response.auth.AuthTokenResponse
import app.hdj.datepick.utils.PlatformLogger
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import com.russhwolf.settings.Settings
import kotlinx.datetime.Clock

@Singleton
class AuthTokenManager @Inject constructor(
    @Named("encrypted") private val settings: Settings
) {

    private val currentTimeInMillis get() = Clock.System.now().toEpochMilliseconds()

    fun setToken(token: AuthTokenResponse) {
        settings.putString(KEY_ACCESS_TOKEN, token.accessToken)
        settings.putString(KEY_REFRESH_TOKEN, token.refreshToken)
        settings.putLong(KEY_EXPIRE_AT, currentTimeInMillis + token.expireIn * 1000)
    }

    fun getAccessToken(): String? {
        return settings.getStringOrNull(KEY_ACCESS_TOKEN)
    }

    fun getRefreshToken(): String? {
        return settings.getStringOrNull(KEY_REFRESH_TOKEN)
    }

    fun isTokenExpired(): Boolean {
        return (settings.getLongOrNull(KEY_EXPIRE_AT) ?: 0L) <= currentTimeInMillis
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_EXPIRE_AT = "expire_at"
    }
}