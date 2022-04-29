package app.hdj.datepick.presentation.utils

import app.hdj.datepick.domain.LoadState
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.toLoadState(default: T? = null): Flow<LoadState<T>> =
    map { LoadState.success(it) as LoadState<T> }
        .onStart { emit(LoadState.loading()) }
        .catch { cause -> emit(LoadState.failed(cause, default)) }

fun <T> Flow<LoadState<T>>.onlyAtSuccess(): Flow<LoadState.Success<T>> =
    filter { it is LoadState.Success }.map { it as LoadState.Success<T> }