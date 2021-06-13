package app.hdj.datepick.android.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import app.hdj.datepick.ui.styles.DatePickTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatePickTheme {
                MainUi()
            }
        }
    }

}
