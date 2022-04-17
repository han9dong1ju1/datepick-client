package app.hdj.datepick.android.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import app.hdj.datepick.android.service.PushNotificationManager
import app.hdj.datepick.domain.settings.AppSettings.AppTheme.*
import app.hdj.datepick.presentation.DatePickAppViewModel
import app.hdj.datepick.utils.location.LocationTracker
import coil.Coil
import coil.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val appViewModel by viewModels<DatePickAppViewModel>()

    @Inject
    lateinit var pushNotificationManager: PushNotificationManager

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var locationTracker: LocationTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        Coil.setImageLoader(imageLoader)

        setupSplashScreen(splashScreen)
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

            DatePickApp(
                appViewModel,
                locationTracker
            )

        }
    }


    private fun setupSplashScreen(splashScreen: SplashScreen) {

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            with(ObjectAnimator.ofFloat(splashScreenView.view, View.ALPHA, 1f, 0f)) {
                duration = 450L
                doOnEnd { splashScreenView.remove() }
                start()
            }
        }

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (!appViewModel.state.value.isLoading) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )

    }

}