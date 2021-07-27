package app.hdj.datepick.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import app.hdj.datepick.android.ui.components.DatePickApp
import app.hdj.datepick.android.ui.components.DatePickAppViewModel
import app.hdj.datepick.android.ui.providers.ProvideMeState
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.domain.entity.settings.AppTheme
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataStore: AppDataStore

    private val appViewModel by viewModels<DatePickAppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        appViewModel.state.onEach {
            AppCompatDelegate.setDefaultNightMode(
                when (it.appTheme) {
                    AppTheme.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    AppTheme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                    AppTheme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                }
            )
        }.launchIn(lifecycleScope)

        setup()
    }

    private fun setup() {
        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .build()

        setContent {

            val (state) = appViewModel.extract()

            DatePickTheme(state.appTheme) {

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        Color.Transparent,
                        darkIcons = useDarkIcons,
                        isNavigationBarContrastEnforced = false
                    )
                }

                ProvideWindowInsets {
                    CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                        ProvideMeState(state.me) {
                            DatePickApp()
                        }
                    }
                }
            }
        }
    }

}