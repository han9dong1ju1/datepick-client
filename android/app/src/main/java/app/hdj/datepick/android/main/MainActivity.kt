package app.hdj.datepick.android.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import app.hdj.datepick.ui.DatePickApp
import app.hdj.datepick.ui.DatePickAppViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.providers.ProvideAppDataStore
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.domain.entity.AppTheme
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
        setContent {

            val (state) = appViewModel.extract()

            DatePickTheme(state.appTheme) {

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight

                SideEffect {
                    systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
                }

                ProvideWindowInsets {
                    ProvideAppDataStore(dataStore = dataStore) {
                        DatePickApp()
                    }
                }
            }
        }
    }

}