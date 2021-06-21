package app.hdj.shared.client.domain.repo

import app.hdj.shared.client.utils.AppTheme

interface SettingRepository {

    fun getAppTheme() : AppTheme

}