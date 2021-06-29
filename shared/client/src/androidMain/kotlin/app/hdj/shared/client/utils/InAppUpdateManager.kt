package app.hdj.shared.client.utils

import android.content.Context
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.ktx.requestCompleteUpdate
import com.google.android.play.core.ktx.requestUpdateFlow
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InAppUpdateManager @Inject constructor(@ApplicationContext context: Context) {

    private val appUpdateManager = AppUpdateManagerFactory.create(context)

    val appUpdateFlow get() = appUpdateManager.requestUpdateFlow()

    suspend fun requestCompleteUpdate() {
        appUpdateManager.requestCompleteUpdate()
    }

}