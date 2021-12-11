package app.hdj.datepick.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import app.hdj.datepick.android.service.PushNotificationManager
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.ProvideDeviceType
import app.hdj.datepick.android.ui.providers.ProvideToastPresenter
import app.hdj.datepick.domain.model.settings.AppTheme
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.disk.DiskCache
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

        appViewModel.state.onEach {
            AppCompatDelegate.setDefaultNightMode(
                when (it.appTheme) {
                    AppTheme.System -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    AppTheme.Dark -> AppCompatDelegate.MODE_NIGHT_YES
                    AppTheme.Light -> AppCompatDelegate.MODE_NIGHT_NO
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

                ProvideToastPresenter {
                    BaseTheme(
                        isDarkTheme = when (state.appTheme) {
                            AppTheme.Dark -> true
                            AppTheme.Light -> false
                            AppTheme.System -> isSystemInDarkTheme()
                        }
                    ) {
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