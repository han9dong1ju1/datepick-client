package app.hdj.datepick.android

import android.app.Application
import app.hdj.datepick.utils.isDebug
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        }
    }

}