package app.hdj.shared.client.data.repo

import app.hdj.shared.client.domain.repo.SettingRepository
import app.hdj.shared.client.utils.DataStoreDelegate
import com.russhwolf.settings.ExperimentalSettingsApi

@OptIn(ExperimentalSettingsApi::class)
open class SettingRepositoryImp(private val dataStore: DataStoreDelegate) : SettingRepository {

    override fun getAppTheme() = dataStore.appTheme




}