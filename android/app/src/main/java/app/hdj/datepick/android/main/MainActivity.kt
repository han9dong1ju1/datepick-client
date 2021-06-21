package app.hdj.datepick.android.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import app.hdj.datepick.ui.DatePickApp
import app.hdj.datepick.ui.styles.DatePickTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight

            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
            }

            DatePickTheme {
                ProvideWindowInsets {
                    DatePickApp()
                }
            }
        }
    }

}