package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun PreviewScope(
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalAppNavController provides rememberNavController()
    ) {
        ProvideWindowInsets(content = content)
    }

}