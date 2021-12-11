package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.model.settings.AppTheme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val theme : Flow<AppTheme>
    suspend fun updateTheme(appTheme: AppTheme)

}