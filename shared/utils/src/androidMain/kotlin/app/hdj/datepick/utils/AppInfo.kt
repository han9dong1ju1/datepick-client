package app.hdj.datepick.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
actual class AppInfo @Inject constructor(@ApplicationContext context: Context) {
    actual val appId: String = context.packageName
    actual val debug: Boolean = BuildConfig.DEBUG
    actual val os: String = "android"
}