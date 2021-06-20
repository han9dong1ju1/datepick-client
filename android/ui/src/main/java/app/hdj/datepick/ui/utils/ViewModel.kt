package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.hdj.datepick.ui.StateViewModel
import kotlinx.coroutines.flow.Flow

data class ViewModelComponent<S, EF, E>(
    val state: S,
    val effect: Flow<EF>,
    val dispatch: (E) -> Unit
)

@Composable
fun <S, EF, E> StateViewModel<S, EF, E>.extract(): ViewModelComponent<S, EF, E> {

    val state by state.collectAsState()

    val dispatch: (E) -> Unit = { event -> event(event) }

    return ViewModelComponent(
        state = state,
        effect = effect,
        dispatch = dispatch
    )
}

@Composable
fun <S, EF, E> extractForPreview(vm : StateViewModel<S, EF, E>): ViewModelComponent<S, EF, E> {

    val state by vm.state.collectAsState()

    val dispatch: (E) -> Unit = { event -> vm.event(event) }

    return ViewModelComponent(
        state = state,
        effect = vm.effect,
        dispatch = dispatch
    )
}