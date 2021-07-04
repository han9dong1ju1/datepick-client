package app.hdj.shared.client.utils

import android.app.Activity
import android.content.Context
import app.hdj.shared.client.domain.entity.AppConfig
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.testing.FakeAppUpdateManager
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.ktx.AppUpdateResult
import com.google.android.play.core.ktx.requestUpdateFlow
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InAppUpdateManager @Inject constructor(
    private val appConfig: AppConfig,
    @ApplicationContext context: Context
) {

    private val appUpdateManager = if (isDebug) FakeAppUpdateManager(context)
    else AppUpdateManagerFactory.create(context)

    val appUpdateFlow = appUpdateManager.requestUpdateFlow()
            .onEach {
                Timber.d("InAppUpdateManager : $it")
            }
            .onStart {
                if (appUpdateManager is FakeAppUpdateManager) {
                    appUpdateManager.setUpdateAvailable(10_000_000)
                    appUpdateManager.isConfirmationDialogVisible
                }
            }

    suspend fun requestUpdate(activity: Activity, result: AppUpdateResult.Available) {
        if (appUpdateManager is FakeAppUpdateManager) {
            Timber.d("FakeAppUpdateManager : userAcceptsUpdate")
            appUpdateManager.userAcceptsUpdate()
            delay(1000)
            Timber.d("FakeAppUpdateManager : downloadStarts")
            appUpdateManager.downloadStarts()
            delay(1000)
            Timber.d("FakeAppUpdateManager : downloadCompletes")
            appUpdateManager.downloadCompletes()
        } else {
            result.startFlexibleUpdate(activity, 0)
        }
    }

    suspend fun completeUpdate(result: AppUpdateResult.Downloaded) {
        result.completeUpdate()

        if (appUpdateManager is FakeAppUpdateManager) {
            delay(1000)
            Timber.d("FakeAppUpdateManager : completeUpdate")
            appUpdateManager.installCompletes()
            Timber.d("FakeAppUpdateManager : installCompletes")
        }
    }

}