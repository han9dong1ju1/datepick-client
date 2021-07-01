package app.hdj.shared.client.data.datastore

import app.hdj.shared.client.domain.entity.settings.AppTheme
import app.hdj.shared.client.domain.entity.settings.NotificationSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSettingsApi::class)
abstract class AppDataStore(private val settings: FlowSettings) : AuthDataStore, SettingDataStore {

    override val appTheme: Flow<AppTheme>
        get() = settings.getIntFlow(KEY_APP_THEME, AppTheme.SYSTEM.value)
            .map { saved -> AppTheme.values().first { it.value == saved } }

    override suspend fun updateTheme(appTheme: AppTheme) =
        settings.putInt(KEY_APP_THEME, appTheme.value)

    override val notificationSettings: Flow<NotificationSettings>
        get() = settings.getStringOrNullFlow(KEY_NOTIFICATION_SETTINGS)
            .map { it?.let(Json.Default::decodeFromString) ?: NotificationSettings() }

    override suspend fun updateNotificationSettings(notificationSettings: NotificationSettings) {
        settings.putString(KEY_NOTIFICATION_SETTINGS, Json.encodeToString(notificationSettings))
    }

    private val _idToken = MutableStateFlow<String?>(null)
    override val idToken: StateFlow<String?> = _idToken
    override suspend fun setIdToken(token: String) {
        _idToken.emit(token)
    }

    suspend fun clear() = settings.clear()

    companion object {
        private const val KEY_APP_THEME = "KEY_APP_THEME"
        private const val KEY_NOTIFICATION_SETTINGS = "KEY_NOTIFICATION_SETTINGS"
    }

}