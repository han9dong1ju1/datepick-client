package app.hdj.datepick.android.utils

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateFailed
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.domain.isStateSucceed

@SuppressLint("ComposableNaming")
@Composable
fun <T> LoadState<T>.foldCrossfade(
    modifier: Modifier = Modifier,
    onSuccess: @Composable (T) -> Unit = {},
    onLoading: @Composable () -> Unit = {},
    onFailed: @Composable (T?, Throwable) -> Unit = { _, _ -> },
) {
    Crossfade(
        modifier = modifier,
        targetState = this
    ) { state ->
        when {
            state.isStateSucceed() -> onSuccess(state.data)
            state.isStateLoading() -> onLoading()
            state.isStateFailed() -> onFailed(state.cachedData, state.throwable)
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun <T> LoadState<T>.onSucceedComposable(block: @Composable (T) -> Unit) {
    if (isStateSucceed()) block(data)
}

@SuppressLint("ComposableNaming")
@Composable
fun <T> LoadState<T>.foldComposable(
    onSuccess: @Composable (T) -> Unit = {},
    onFailed: @Composable (T?, Throwable) -> Unit = { _, _ -> },
    onLoading: @Composable () -> Unit = {},
) {
    when {
        isStateSucceed() -> onSuccess(data)
        isStateFailed() -> onFailed(cachedData, throwable)
        else -> onLoading()
    }
}