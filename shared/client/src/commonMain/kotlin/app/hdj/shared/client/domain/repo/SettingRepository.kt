package app.hdj.shared.client.domain.repo

import app.hdj.shared.client.utils.AppTheme
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    fun getAppTheme() : Flow<AppTheme>

}