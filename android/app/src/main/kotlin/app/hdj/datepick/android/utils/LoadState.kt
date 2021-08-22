package app.hdj.datepick.android.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateFailed
import app.hdj.datepick.domain.isStateSucceed

@SuppressLint("ComposableNaming")
@Composable
fun <T> LoadState<T>.onSucceedComposable(block: @Composable (T) -> Unit) {
    if (isStateSucceed()) block(data)
}

@SuppressLint("ComposableNaming")
@Composable
fun <T> LoadState<T>.foldComposable(
    onSucceed: @Composable (T) -> Unit = {},
    onFailed: @Composable (T?, Throwable) -> Unit = { _, _ -> },
    onLoading: @Composable () -> Unit = {},
) {
    when {
        isStateSucceed() -> onSucceed(data)
        isStateFailed() -> onFailed(cachedData, throwable)
        else -> onLoading()
    }
}