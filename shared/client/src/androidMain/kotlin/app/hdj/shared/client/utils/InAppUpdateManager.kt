package app.hdj.shared.client.utils

import android.content.Context
import app.hdj.shared.client.domain.entity.AppConfig
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.testing.FakeAppUpdateManager
import com.google.android.play.core.ktx.AppUpdateResult
import com.google.android.play.core.ktx.requestUpdateFlow
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InAppUpdateManager @Inject constructor(
    private val appConfig: AppConfig,
    @ApplicationContext context: Context
) {

    private val appUpdateManager = if (isDebug) FakeAppUpdateManager(context)
    else AppUpdateManagerFactory.create(context)

    val appUpdateFlow
        get() = appUpdateManager.requestUpdateFlow()
            .onEach {
                if (appUpdateManager is FakeAppUpdateManager) {
                    appUpdateManager.doFakeScenario(it)
                }
            }.onStart {
                if (appUpdateManager is FakeAppUpdateManager) {
                    appUpdateManager.setUpdateAvailable(10_000_000)
                    appUpdateManager.isConfirmationDialogVisible
                }
            }

}

suspend fun FakeAppUpdateManager.doFakeScenario(appUpdateResult: AppUpdateResult) {

    when (appUpdateResult) {
        AppUpdateResult.NotAvailable -> {

        }
        is AppUpdateResult.Available -> {
            userAcceptsUpdate()
            delay(1000)
            downloadStarts()
        }
        is AppUpdateResult.InProgress -> {
            delay(1000)
            downloadCompletes()
        }
        is AppUpdateResult.Downloaded -> {
            appUpdateResult.completeUpdate()
            delay(1000)
            installCompletes()
        }
    }


}