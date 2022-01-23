package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.DatePickAppViewModel
import app.hdj.datepick.presentation.DatePickAppViewModelDelegate
import app.hdj.datepick.utils.location.LocationTracker
import coil.ImageLoader
import coil.compose.LocalImageLoader
import com.google.accompanist.insets.ProvideWindowInsets

val LocalDatePickAppViewModel = staticCompositionLocalOf<DatePickAppViewModelDelegate> {
    error("Not Provided")
}

@Composable
fun DatePickComposableProviderScope(
    appViewModel: DatePickAppViewModel,
    imageLoader: ImageLoader,
    locationTracker: LocationTracker,
    content: @Composable () -> Unit
) {
    val (state) = appViewModel.extract()
    CompositionLocalProvider(
        LocalDatePickAppViewModel provides appViewModel,
        LocalMe provides state.me,
        LocalImageLoader provides imageLoader,
        LocalLocationTracker provides locationTracker
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