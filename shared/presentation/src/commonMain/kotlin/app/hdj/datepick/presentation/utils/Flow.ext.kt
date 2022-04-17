package app.hdj.datepick.presentation.utils

import app.hdj.datepick.domain.LoadState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.toLoadState(default: T? = null): Flow<LoadState<T>> =
    map { LoadState.success(it) as LoadState<T> }
        .onStart { emit(LoadState.loading()) }
        .catch { cause -> emit(LoadState.failed(cause, default)) }