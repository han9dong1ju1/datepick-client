package app.hdj.datepick.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.with
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import app.hdj.datepick.android.service.PushNotificationManager
import app.hdj.datepick.android.ui.providers.*
import app.hdj.datepick.domain.settings.AppSettings.AppTheme.*
import app.hdj.datepick.ui.animation.materialTransitionZaxisIn
import app.hdj.datepick.ui.animation.materialTransitionZaxisOut
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import coil.ImageLoader
import coil.disk.DiskCache
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import app.hdj.datepick.android.R
import app.hdj.datepick.android.ui.icons.DatePickIcon
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.utils.MarkdownGenerator
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val appViewModel by viewModels<DatePickAppViewModel>()

    @Inject
    lateinit var pushNotificationManager: PushNotificationManager

    @Inject
    lateinit var imageLoader: ImageLoader

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

        setContent {

            DatePickComposableProviderScope(appViewModel, imageLoader) {

                val (state) = appViewModel.extract()

                val isSystemInDarkTheme = isSystemInDarkTheme()

                val isDarkTheme = when (state.appTheme) {
                    Light -> false
                    Dark -> true
                    System -> isSystemInDarkTheme
                    else -> isSystemInDarkTheme
                }

                val systemUiController = rememberSystemUiController()

                LaunchedEffect(isDarkTheme) {
                    systemUiController.systemBarsDarkContentEnabled = !isDarkTheme
                }

                BaseTheme(
                    isDarkTheme = isDarkTheme,
                ) {
                    DatePickApp()
                }
            }

        }
    }

}