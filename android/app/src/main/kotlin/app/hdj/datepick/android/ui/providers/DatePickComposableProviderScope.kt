package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import app.hdj.datepick.android.ui.DatePickAppViewModel
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.utils.extract
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun DatePickComposableProviderScope(
    appViewModel : DatePickAppViewModel,
    imageLoader : ImageLoader,
    content : @Composable () -> Unit
) {
    val (state) = appViewModel.extract()
    CompositionLocalProvider(
        LocalDatePickAppViewModel provides appViewModel,
        LocalMe provides state.me,
        LocalImageLoader provides imageLoader
    ) {
        ProvideToastPresenter {
            ProvideWindowInsets {
                ProvideDeviceType {
                    content()
                }
            }
        }
    }
}