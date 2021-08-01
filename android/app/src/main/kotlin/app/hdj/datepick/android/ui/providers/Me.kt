package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.data.model.User

val LocalMeState = compositionLocalOf<app.hdj.datepick.domain.StateData<app.hdj.datepick.data.model.User>> { app.hdj.datepick.domain.StateData.Loading() }

@Composable
fun ProvideMeState(me : app.hdj.datepick.domain.StateData<app.hdj.datepick.data.model.User>, content : @Composable () -> Unit) {
    CompositionLocalProvider(LocalMeState provides me) {
        content()
    }
}