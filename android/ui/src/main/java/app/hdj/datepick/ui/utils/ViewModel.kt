package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ViewModelDelegate<S, EF, E> {

    val state: StateFlow<S>

    val effect: Flow<EF>

    fun event(event: E)

}

data class ViewModelComponent<S, EF, E>(
    val state: S,
    val effect: Flow<EF>,
    val dispatch: (E) -> Unit
)

@Composable
fun <S, EF, E> ViewModelDelegate<S, EF, E>.extract(): ViewModelComponent<S, EF, E> {

    val state by state.collectAsState()

    val dispatch: (E) -> Unit = { event -> event(event) }

    return ViewModelComponent(
        state = state,
        effect = effect,
        dispatch = dispatch
    )
}