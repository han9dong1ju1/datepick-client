package app.hdj.shared.client.data.repo

import app.hdj.shared.client.domain.repo.SettingRepository
import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.domain.entity.AppTheme
import app.hdj.shared.client.domain.entity.NotificationSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalSettingsApi::class)
open class SettingRepositoryImp(private val appDataStore: AppDataStore) : SettingRepository {

    override fun getAppTheme() = appDataStore.appTheme

    override suspend fun updateAppTheme(appTheme: AppTheme) {
        TODO("Not yet implemented")
    }

    override fun getNotificationSettings(): Flow<NotificationSettings> {
        TODO("Not yet implemented")
    }

    override suspend fun updateNotificationSettings(settings: NotificationSettings) {
        TODO("Not yet implemented")
    }

}