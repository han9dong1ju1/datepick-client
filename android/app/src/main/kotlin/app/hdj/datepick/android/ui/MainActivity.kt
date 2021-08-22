package app.hdj.datepick.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import app.hdj.datepick.android.ui.StatusBarMode.*
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.ProvideToastPresenter
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val appViewModel by viewModels<DatePickAppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashWasDisplayed = savedInstanceState != null
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (!splashWasDisplayed) {
            installSplashScreen()
            setup()
        } else {
            setup()
        }


//        appViewModel.state.onEach {
//            AppCompatDelegate.setDefaultNightMode(
//                when (it.appTheme) {
//                    app.hdj.datepick.data.model.settings.AppTheme.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
//                    app.hdj.datepick.data.model.settings.AppTheme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
//                    app.hdj.datepick.data.model.settings.AppTheme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
//                }
//            )
//        }.launchIn(lifecycleScope)

    }

    private fun setup() {
        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .build()

        setContent {

            val (state, _) = appViewModel.extract()

            CompositionLocalProvider(
                LocalDatePickAppViewModel provides appViewModel,
                LocalMe provides state.me,
                LocalImageLoader provides imageLoader
            ) {

                val systemUiController = rememberSystemUiController()
                val statusBarMode = state.statusBarMode
                val useDarkIcons = MaterialTheme.colors.isLight
                val statusBarScrim = MaterialTheme.colors.background
                val statusBarScrimWithTransparency = MaterialTheme.colors.background.copy(0.0f)

                SideEffect {
                    when (statusBarMode) {
                        STATUS_BAR_FORCE_WHITE ->
                            systemUiController.setStatusBarColor(Color.Transparent, false)
                        STATUS_BAR_SYSTEM ->
                            systemUiController.setStatusBarColor(statusBarScrim, useDarkIcons)
                        STATUS_BAR_SYSTEM_WITH_TRANSPARENCY ->
                            systemUiController.setStatusBarColor(
                                statusBarScrimWithTransparency,
                                useDarkIcons
                            )
                    }
                }

                ProvideToastPresenter {
                    DatePickTheme {
                        ProvideWindowInsets { DatePickApp() }
                    }
                }

            }

        }
    }

}