package app.hdj.datepick.android.utils

import android.annotation.SuppressLint
import androidx.compose.animation.*
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
fun <T> LoadState<T>.onSucceedComposable(block: @Composable (T) -> Unit): LoadState<T> {
    if (isStateSucceed()) block(data)
    return this
}

@SuppressLint("ComposableNaming")
@Composable
fun <T> LoadState<T>.onLoadingComposable(block: @Composable () -> Unit): LoadState<T> {
    if (isStateLoading()) block()
    return this
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

@SuppressLint("ComposableNaming")
@Composable
fun <T> LoadStateAnimatedContent(
    modifier: Modifier = Modifier,
    loadState: LoadState<T>,
    transitionSpec: AnimatedContentScope<LoadState<T>>.() -> ContentTransform = { fadeIn() with fadeOut() },
    onSuccess: @Composable (T) -> Unit = {},
    onFailed: @Composable (T?, Throwable) -> Unit = { _, _ -> },
    onLoading: @Composable () -> Unit = {},
) {
    AnimatedContent(
        modifier = modifier,
        targetState = loadState,
        transitionSpec = transitionSpec
    ) { state ->
        when {
            state.isStateSucceed() -> onSuccess(state.data)
            state.isStateFailed() -> onFailed(state.cachedData, state.throwable)
            else -> onLoading()
        }
    }
}