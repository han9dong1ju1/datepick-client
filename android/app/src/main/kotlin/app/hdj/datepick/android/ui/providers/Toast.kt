package app.hdj.datepick.android.ui.providers

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext

interface ToastPresenter {
    fun short(message: String)
    fun long(message: String)
}

val LocalToastPresenter = compositionLocalOf<ToastPresenter> {
    object : ToastPresenter {
        override fun short(message: String) {

        }

        override fun long(message: String) {

        }
    }
}

@Composable
fun ProvideToastPresenter(content: @Composable () -> Unit) {
    val context = LocalContext.current
    CompositionLocalProvider(LocalToastPresenter provides object : ToastPresenter {
        override fun short(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        override fun long(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }) {
        content()
    }
}