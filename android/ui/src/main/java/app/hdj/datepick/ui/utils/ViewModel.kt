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
fun <S, EF, E> viewModelComponent(viewModel: StateViewModel<S, EF, E>): ViewModelComponent<S, EF, E> {

    val state by viewModel.state.collectAsState()

    val dispatch: (E) -> Unit = { event ->
        viewModel.event(event)
    }

    return ViewModelComponent(
        state = state,
        effect = viewModel.effect,
        dispatch = dispatch
    )
}