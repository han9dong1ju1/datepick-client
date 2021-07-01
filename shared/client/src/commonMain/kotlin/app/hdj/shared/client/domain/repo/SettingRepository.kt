package app.hdj.shared.client.domain.repo

import app.hdj.shared.client.domain.entity.settings.AppTheme
import app.hdj.shared.client.domain.entity.settings.NotificationSettings
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    fun getAppTheme(): Flow<AppTheme>

    suspend fun updateAppTheme(appTheme: AppTheme)

    fun getNotificationSettings(): Flow<NotificationSettings>

    suspend fun updateNotificationSettings(settings: NotificationSettings)

}