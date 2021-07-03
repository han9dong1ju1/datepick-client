package app.hdj.datepick.android.ui.providers

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class SnackbarData(
    val message: String,
    val actionLabel: String? = null,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val onSnackbarResult: suspend (SnackbarResult) -> Unit
)

class SnackbarPresenter(
    private val coroutineScope: CoroutineScope,
    private val scaffoldState: ScaffoldState
) {

    fun showSnackBar(
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short,
        onSnackbarResult: suspend (SnackbarResult) -> Unit = { }
    ) {
        coroutineScope.launch {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message, actionLabel, duration
            )
            onSnackbarResult(result)
        }
    }

}

val LocalSnackBarPresenter = compositionLocalOf<SnackbarPresenter> {
    error("Not Provided!")
}

@Composable
fun ProvideLocalSnackBarPresenter(scaffoldState: ScaffoldState, content: @Composable () -> Unit) {

    val coroutineScope = rememberCoroutineScope()

    CompositionLocalProvider(
        LocalSnackBarPresenter provides SnackbarPresenter(coroutineScope, scaffoldState)
    ) {
        content()
    }

}