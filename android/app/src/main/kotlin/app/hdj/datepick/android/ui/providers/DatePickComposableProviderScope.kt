package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.DatePickAppViewModel
import app.hdj.datepick.presentation.DatePickAppViewModelDelegate
import app.hdj.datepick.utils.location.LocationTracker

val LocalDatePickAppViewModel = staticCompositionLocalOf<DatePickAppViewModelDelegate> {
    error("Not Provided")
}

@Composable
fun DatePickComposableProviderScope(
    appViewModel: DatePickAppViewModel,
    locationTracker: LocationTracker,
    content: @Composable () -> Unit
) {
    val (state) = appViewModel.extract()
    CompositionLocalProvider(
        LocalDatePickAppViewModel provides appViewModel,
        LocalMe provides state.me,
        LocalLocationTracker provides locationTracker
    ) {
        ProvideToastPresenter {
            ProvideDeviceType {
                content()
            }
        }
    }
}