package app.hdj.datepick.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import app.hdj.datepick.android.service.PushNotificationManager
import app.hdj.datepick.domain.settings.AppSettings.AppTheme.*
import app.hdj.datepick.domain.usecase.course.GetRecommendedCoursesUseCase
import app.hdj.datepick.presentation.DatePickAppViewModel
import app.hdj.datepick.utils.PlatformLogger
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
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        Coil.setImageLoader(imageLoader)

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

}