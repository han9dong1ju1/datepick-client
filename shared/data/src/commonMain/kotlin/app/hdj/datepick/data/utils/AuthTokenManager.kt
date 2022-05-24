package app.hdj.datepick.data.utils

import app.hdj.datepick.data.model.response.auth.AuthTokenResponse
import app.hdj.datepick.utils.PlatformLogger
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import com.russhwolf.settings.Settings
import io.ktor.client.plugins.auth.providers.*
import kotlinx.datetime.Clock

@Singleton
class AuthTokenManager @Inject constructor(
    @Named("encrypted") private val settings: Settings
) {

    private val currentTimeInMillis get() = Clock.System.now().toEpochMilliseconds()

    init {
        val accessToken = accessToken
        val refreshToken = refreshToken
        if (accessToken != null && refreshToken != null) {
            _tokenStorage.add(BearerTokens(accessToken, refreshToken))
        }
    }

    fun setToken(token: AuthTokenResponse) {
        settings.putString(KEY_ACCESS_TOKEN, token.accessToken)
        settings.putString(KEY_REFRESH_TOKEN, token.refreshToken)
        settings.putLong(KEY_EXPIRE_AT, currentTimeInMillis + token.expireIn * 1000)
        addToken(token)
    }

    private val accessToken: String?
        get() = settings.getStringOrNull(KEY_ACCESS_TOKEN)

    private val refreshToken: String?
        get() = settings.getStringOrNull(KEY_REFRESH_TOKEN)

    fun isTokenExpired(): Boolean {
        return (settings.getLongOrNull(KEY_EXPIRE_AT) ?: 0L) <= currentTimeInMillis
    }

    fun clear() {
        settings.remove(KEY_ACCESS_TOKEN)
        settings.remove(KEY_REFRESH_TOKEN)
        settings.remove(KEY_EXPIRE_AT)
        _tokenStorage.clear()
    }

    companion object {
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_EXPIRE_AT = "expire_at"

        private val _tokenStorage = mutableListOf<BearerTokens>()
        val tokenStorage: List<BearerTokens>
            get() = _tokenStorage

        fun addToken(token: AuthTokenResponse) {
            _tokenStorage.add(BearerTokens(token.accessToken, token.refreshToken))
        }
    }
}