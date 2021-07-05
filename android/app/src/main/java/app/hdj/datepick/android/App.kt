package app.hdj.datepick.android

import android.app.Application
import app.hdj.shared.client.utils.isDebug
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        }

        Places.initialize(applicationContext, BuildConfig.GOOGLE_CLOUD_PLACE_API_KEY)
    }

}