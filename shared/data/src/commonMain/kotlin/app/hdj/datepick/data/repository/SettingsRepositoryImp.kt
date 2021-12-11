package app.hdj.datepick.data.repository

import app.hdj.datepick.domain.model.settings.AppTheme
import app.hdj.datepick.domain.repository.SettingsRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalSettingsApi::class)
@Singleton
class SettingsRepositoryImp @Inject constructor(
    private val flowSettings: FlowSettings
) : SettingsRepository {
    override val theme: Flow<AppTheme> = flowSettings.getStringFlow(SETTINGS_APP_THEME)
        .map {
            when (it) {
                SETTINGS_APP_THEME_DARK -> AppTheme.Dark
                SETTINGS_APP_THEME_LIGHT -> AppTheme.Light
                else -> AppTheme.System
            }
        }

    override suspend fun updateTheme(appTheme: AppTheme) {
        val value = when (appTheme) {
            AppTheme.Dark -> SETTINGS_APP_THEME_DARK
            AppTheme.Light -> SETTINGS_APP_THEME_LIGHT
            AppTheme.System -> SETTINGS_APP_THEME_SYSTEM
        }
        flowSettings.putString(SETTINGS_APP_THEME, value)
    }

    companion object {
        private const val SETTINGS_APP_THEME = "settings_app_theme"
        private const val SETTINGS_APP_THEME_DARK = "dark"
        private const val SETTINGS_APP_THEME_LIGHT = "light"
        private const val SETTINGS_APP_THEME_SYSTEM = "system"
    }
}