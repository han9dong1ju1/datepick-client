package app.hdj.shared.client.utils

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalSettingsApi::class)
abstract class DataStoreDelegate(private val settings: FlowSettings) {

    val appTheme: Flow<AppTheme> = settings.getIntFlow(KEY_APP_THEME, AppTheme.SYSTEM.value)
            .map { saved -> AppTheme.values().first { it.value == saved } }

    suspend fun setTheme(appTheme: AppTheme) {
        settings.putInt(KEY_APP_THEME, AppTheme.values().first { it == appTheme }.value)
    }

    companion object {
        private const val KEY_APP_THEME = "app_theme"
    }

}