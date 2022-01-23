package app.hdj.datepick.domain.settings

import kotlinx.coroutines.flow.Flow

interface AppSettings {

    enum class AppTheme {
        Light, Dark, System
    }

    val appTheme : Flow<AppTheme>
    suspend fun updateAppTheme(appTheme: AppTheme)

    val ignoreNearbyRecommend : Flow<Boolean?>
    suspend fun setIgnoreNearbyRecommend(isIgnored: Boolean)

}