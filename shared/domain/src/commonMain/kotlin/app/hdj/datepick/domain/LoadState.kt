package app.hdj.datepick.domain

import app.hdj.datepick.domain.LoadState.Companion.failed
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.LoadState.Companion.success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed interface LoadState<T> {

    data class Success<T>(val data: T) : LoadState<T>
    class Loading<T> : LoadState<T>
    class Failed<T>(val throwable: Throwable, val cachedData: T? = null) : LoadState<T>

    companion object {

        fun <T> success(data: T) = Success(data)
        fun <T> loading() = Loading<T>()
        fun <T> failed(throwable: Throwable, data: T? = null) = Failed(throwable, data)

    }

}

fun <T, R> LoadState<T>.map(mapper : (T) -> R) : LoadState<R> {
    return when (this) {
        is LoadState.Failed -> LoadState.Failed(throwable, cachedData?.let { mapper(it) })
        is LoadState.Loading -> LoadState.Loading()
        is LoadState.Success -> LoadState.Success(mapper(data))
    }
}

suspend fun <T : Any> FlowCollector<LoadState<T>>.emitState(
    defaultValue: T? = null,
    onSuccess: suspend (T) -> Unit = {},
    onFailed: suspend (Throwable) -> Unit = {},
    executor: suspend () -> T?,
) {
    emit(loading())
    val state = runCatching {
        requireNotNull(executor())
    }.fold(
        { onSuccess(it); success(it) },
        { onFailed(it); failed(it, defaultValue) }
    )
    emit(state)
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