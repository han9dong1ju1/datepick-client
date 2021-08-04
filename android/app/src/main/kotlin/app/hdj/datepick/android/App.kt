package app.hdj.datepick.android

import android.app.Application
import app.hdj.datepick.utils.AppInfo
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var appInfo: AppInfo

    override fun onCreate() {
        super.onCreate()
        if (appInfo.debug) {
            Timber.plant(Timber.DebugTree())
        }
    }

}