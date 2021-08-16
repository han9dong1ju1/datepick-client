package app.hdj.datepick.android

import android.app.Application
import app.hdj.datepick.android.utils.FirebaseCrashlyticsTree
import app.hdj.datepick.utils.AppInfo
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var appInfo: AppInfo

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        if (appInfo.debug) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(FirebaseCrashlyticsTree())
        }
    }

}