package app.hdj.shared.client.data.repo

import app.hdj.shared.client.domain.repo.SettingRepository
import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.domain.entity.settings.AppTheme
import app.hdj.shared.client.domain.entity.settings.NotificationSettings
import com.russhwolf.settings.ExperimentalSettingsApi

@OptIn(ExperimentalSettingsApi::class)
open class SettingRepositoryImp(private val appDataStore: AppDataStore) : SettingRepository {

    override fun getAppTheme() = appDataStore.appTheme

    override suspend fun updateAppTheme(appTheme: AppTheme) {
        appDataStore.updateTheme(appTheme)
    }

    override fun getNotificationSettings() = appDataStore.notificationSettings

    override suspend fun updateNotificationSettings(settings: NotificationSettings) {
        appDataStore.updateNotificationSettings(settings)
    }

}