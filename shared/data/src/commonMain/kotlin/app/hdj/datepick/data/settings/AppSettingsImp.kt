@file:Suppress("EXPERIMENTAL_API_USAGE")

package app.hdj.datepick.data.settings

import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class AppSettingsImp @Inject constructor(
    private val flowSettings: FlowSettings
) : AppSettings {

    override val appTheme: Flow<AppSettings.AppTheme> =
        flowSettings.getStringFlow(KEY_APP_THEME).map {
            when (it) {
                "dark" -> AppSettings.AppTheme.Dark
                "light" -> AppSettings.AppTheme.Light
                "system" -> AppSettings.AppTheme.System
                else -> AppSettings.AppTheme.System
            }
        }

    override suspend fun updateAppTheme(appTheme: AppSettings.AppTheme) {
        flowSettings.putString(
            KEY_APP_THEME,
            when (appTheme) {
                AppSettings.AppTheme.Dark -> "dark"
                AppSettings.AppTheme.Light -> "light"
                AppSettings.AppTheme.System -> "system"
                else -> "system"
            }
        )
    }

    companion object {
        private const val KEY_APP_THEME = "app_theme"
        private const val KEY_IS_DYNAMIC_THEME_ENABLED = "is_dynamic_theme_enabled"
    }

}