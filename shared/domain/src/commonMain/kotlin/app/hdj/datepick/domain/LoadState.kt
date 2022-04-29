package app.hdj.datepick.domain

import app.hdj.datepick.domain.LoadState.Companion.failed
import app.hdj.datepick.domain.LoadState.Companion.idle
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.LoadState.Companion.success
import app.hdj.datepick.utils.PlatformLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

typealias EmptyLoadState = LoadState<Unit>

sealed interface LoadState<T> {

    val dataOrNull get() = if (this is Success) data else if (this is Failed) cachedData else null

    class Idle<T> : LoadState<T>
    data class Success<T>(val data: T) : LoadState<T>
    class Loading<T> : LoadState<T>
    class Failed<T>(val throwable: Throwable, val cachedData: T? = null) : LoadState<T> {
        init {
            PlatformLogger.e(throwable)
        }
    }

    companion object {

        fun <T> idle() = Idle<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> loading() = Loading<T>()
        fun <T> failed(throwable: Throwable, data: T? = null) = Failed(throwable, data)

    }

}

fun <T, R> LoadState<T>.map(mapper: (T) -> R): LoadState<R> {
    return when (this) {
        is LoadState.Failed -> failed(throwable, cachedData?.let { mapper(it) })
        is LoadState.Loading -> loading()
        is LoadState.Success -> success(mapper(data))
        is LoadState.Idle -> idle()
    }
}

fun <T, R> LoadState<T>.flatMap(mapper: (T) -> LoadState<R>): LoadState<R> {
    return when (this) {
        is LoadState.Success -> mapper(data)
        is LoadState.Loading -> loading()
        is LoadState.Failed -> failed(throwable, cachedData?.let { mapper(it) }?.dataOrNull)
        is LoadState.Idle -> idle()
    }
}

fun <T, R> LoadState<T>.fold(
    onSuccess: (T) -> R,
    onFailed: (T?, Throwable) -> R? = { _, _ -> null },
    onLoading: () -> R? = { null },
) = when {
    isStateSucceed() -> onSuccess(data)
    isStateFailed() -> onFailed(cachedData, throwable)
    else -> onLoading()
}

fun <T, R> LoadState<T>.mapOrNull(block: (T) -> R): R? {
    return if (isStateSucceed()) block(data) else null
}

fun <T> LoadState<T>.onSucceed(block: (T) -> Unit) {
    if (isStateSucceed()) block(data)
}

fun <T> LoadState<T>.onLoading(block: () -> Unit) {
    if (isStateLoading()) block()
}

fun <T> LoadState<T>.onFailed(block: (T?, Throwable) -> Unit) {
    if (isStateFailed()) block(cachedData, throwable)
}

fun <T> Flow<LoadState<T>>.mapFailedState(mapper: suspend (LoadState.Failed<T>) -> LoadState.Failed<T>) =
    map {
        if (it.isStateFailed()) mapper(it)
        else it
    }

@OptIn(ExperimentalContracts::class)
fun <T> LoadState<T>.isStateSucceed(): Boolean {
    contract {
        returns(true) implies (this@isStateSucceed is LoadState.Success)
    }
    return this@isStateSucceed is LoadState.Success
}

@OptIn(ExperimentalContracts::class)
fun <T> LoadState<T>.isStateFailed(): Boolean {
    contract {
        returns(true) implies (this@isStateFailed is LoadState.Failed)
    }
    return this@isStateFailed is LoadState.Failed
}

@OptIn(ExperimentalContracts::class)
fun <T> LoadState<T>.isStateLoading(): Boolean {
    contract {
        returns(true) implies (this@isStateLoading is LoadState.Loading)
    }
    return this@isStateLoading is LoadState.Loading
}