package app.hdj.datepick.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.User

val LocalMeState = compositionLocalOf<StateData<User>> { StateData.Loading() }

@Composable
fun ProvideMeState(me : StateData<User>, content : @Composable () -> Unit) {
    CompositionLocalProvider(LocalMeState provides me) {
        content()
    }
}