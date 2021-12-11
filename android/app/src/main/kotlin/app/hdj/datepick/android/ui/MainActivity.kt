package app.hdj.datepick.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import app.hdj.datepick.android.service.PushNotificationManager
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.ProvideDeviceType
import app.hdj.datepick.android.ui.providers.ProvideToastPresenter
import app.hdj.datepick.android.ui.providers.preview.FakeUserPreviewProvider
import app.hdj.datepick.android.utils.DEEPLINK_URL
import app.hdj.datepick.android.utils.EXTERNAL_DEEPLINK_URL
import app.hdj.datepick.android.utils.datePickNavDeepLink
import app.hdj.datepick.domain.model.pushNotification.PushNotificationData
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.domain.settings.AppSettings.AppTheme.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.disk.DiskCache
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val appViewModel by viewModels<DatePickAppViewModel>()

    @Inject
    lateinit var pushNotificationManager: PushNotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setup()


        appViewModel.state.mapNotNull { it.appTheme }.onEach {
            AppCompatDelegate.setDefaultNightMode(
                when (it) {
                    System -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    Dark -> AppCompatDelegate.MODE_NIGHT_YES
                    Light -> AppCompatDelegate.MODE_NIGHT_NO
                }
            )
        }.launchIn(lifecycleScope)

    }

    private fun setup() {
        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .diskCache(
                DiskCache.Builder(this)
                    .directory(cacheDir)
                    .build()
            )
            .build()

        setContent {

            val (state, _) = appViewModel.extract()

            CompositionLocalProvider(
                LocalDatePickAppViewModel provides appViewModel,
                LocalMe provides state.me,
                LocalImageLoader provides imageLoader
            ) {

                val systemUiController = rememberSystemUiController()

                val isSystemInDarkTheme = isSystemInDarkTheme()

                val isDarkTheme = when (state.appTheme) {
                    Light -> false
                    Dark -> true
                    System -> isSystemInDarkTheme
                    else -> null
                }

                if (isDarkTheme != null) {
                    LaunchedEffect(isDarkTheme) {
                        systemUiController.systemBarsDarkContentEnabled = !isDarkTheme
                    }

                    ProvideToastPresenter {
                        BaseTheme(isDarkTheme = isDarkTheme) {
                            ProvideWindowInsets {
                                ProvideDeviceType {
                                    DatePickApp()
                                }
                            }
                        }
                    }
                }

            }

        }
    }

}