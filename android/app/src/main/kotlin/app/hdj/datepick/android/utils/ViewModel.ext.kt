package app.hdj.datepick.android.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.hdj.datepick.presentation.PresentationComponent
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate

@Composable
fun <S : Any, E, EF> UnidirectionalViewModelDelegate<S, E, EF>.extract() : PresentationComponent<S, E, EF> {

    val state by state.collectAsState()

    return PresentationComponent(
        state,
        effect = effect,
        dispatch = { event(it) }
    )
}