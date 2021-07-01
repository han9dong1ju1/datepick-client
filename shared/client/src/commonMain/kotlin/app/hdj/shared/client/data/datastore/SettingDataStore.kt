package app.hdj.shared.client.data.datastore

import app.hdj.shared.client.domain.entity.settings.AppTheme
import app.hdj.shared.client.domain.entity.settings.NotificationSettings
import kotlinx.coroutines.flow.Flow

interface SettingDataStore {

    val appTheme : Flow<AppTheme>
    suspend fun updateTheme(appTheme: AppTheme)

    val notificationSettings : Flow<NotificationSettings>
    suspend fun updateNotificationSettings(notificationSettings: NotificationSettings)


}