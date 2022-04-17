package app.hdj.datepick.data.utils

import app.hdj.datepick.data.model.response.auth.AuthToken
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import com.russhwolf.settings.Settings
import kotlinx.datetime.Clock

@Singleton
class AuthTokenManager @Inject constructor(
    @Named("encrypted") private val settings: Settings
) {

    fun setToken(token: AuthToken) {
        settings.putString(KEY_ACCESS_TOKEN, token.accessToken)
        settings.putString(KEY_REFRESH_TOKEN, token.refreshToken)
        settings.putLong(KEY_EXPIRES_IN, token.expiredIn)
    }

    fun getAccessToken(): String? {
        return settings.getStringOrNull(KEY_ACCESS_TOKEN)
    }

    fun getRefreshToken(): String? {
        return settings.getStringOrNull(KEY_REFRESH_TOKEN)
    }

    fun isTokenExpired() = (settings.getLongOrNull(KEY_EXPIRES_IN) ?: 0L) < Clock.System.now()
            .toEpochMilliseconds()

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_EXPIRES_IN = "expires_in"
    }
}