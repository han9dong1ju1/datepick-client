package app.hdj.datepick.domain.settings

import kotlinx.coroutines.flow.Flow

interface AppSettings {

    enum class AppTheme {
        Light, Dark, System
    }

    val appTheme : Flow<AppTheme>
    suspend fun updateAppTheme(appTheme: AppTheme)

    val isNearbyRecommendBannerIgnored : Flow<Boolean?>
    suspend fun setNearbyRecommendBannerIgnored(isIgnored: Boolean)

}