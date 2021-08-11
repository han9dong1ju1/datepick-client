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
import app.hdj.datepick.data.api.ApiResponse
import app.hdj.datepick.data.entity.featured.FeaturedResponse
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import timber.log.Timber

val json = """{"message":null,"code":200,"data":[{"id":1,"title":"title_1","description":"description_1","photoUrl":"photo_url_1"},{"id":2,"title":"title_2","description":"description_2","photoUrl":"photo_url_2"},{"id":3,"title":"title_3","description":"description_3","photoUrl":"photo_url_3"},{"id":4,"title":"title_4","description":"description_4","photoUrl":"photo_url_4"},{"id":5,"title":"title_5","description":"description_5","photoUrl":"photo_url_5"},{"id":6,"title":"title_6","description":"description_6","photoUrl":"photo_url_6"},{"id":7,"title":"title_7","description":"description_7","photoUrl":"photo_url_7"},{"id":8,"title":"title_8","description":"description_8","photoUrl":"photo_url_8"},{"id":9,"title":"title_9","description":"description_9","photoUrl":"photo_url_9"},{"id":10,"title":"title_10","description":"description_10","photoUrl":"photo_url_10"}]}
""".trimIndent()

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

            val (state, _, event) = appViewModel.extract()

            CompositionLocalProvider(
                LocalDatePickAppViewModel provides appViewModel,
                LocalMe provides state.me,
                LocalImageLoader provides imageLoader
            ) {

                val systemUiController = rememberSystemUiController()
                val statusBarMode = state.statusBarMode
                val useDarkIcons = MaterialTheme.colors.isLight
                val statusBarScrim = MaterialTheme.colors.background.copy(0.5f)

                SideEffect {
                    when (statusBarMode) {
                        STATUS_BAR_FORCE_WHITE ->
                            systemUiController.setStatusBarColor(Color.Transparent, false)
                        STATUS_BAR_SYSTEM ->
                            systemUiController.setStatusBarColor(statusBarScrim, useDarkIcons)
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