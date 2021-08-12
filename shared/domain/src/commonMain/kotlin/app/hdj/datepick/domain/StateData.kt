package app.hdj.datepick.domain

import app.hdj.datepick.domain.StateData.Companion.failed
import app.hdj.datepick.domain.StateData.Companion.loading
import app.hdj.datepick.domain.StateData.Companion.success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed interface StateData<T> {

    data class Success<T>(val data: T) : StateData<T>
    class Loading<T> : StateData<T>
    class Failed<T>(val throwable: Throwable, val cachedData: T? = null) : StateData<T>

    companion object {

        fun <T> success(data: T) = Success(data)
        fun <T> loading() = Loading<T>()
        fun <T> failed(throwable: Throwable, data: T? = null) = Failed(throwable, data)

    }

}

fun <T, R> StateData<T>.map(mapper : (T) -> R) : StateData<R> {
    return when (this) {
        is StateData.Failed -> StateData.Failed(throwable, cachedData?.let { mapper(it) })
        is StateData.Loading -> StateData.Loading()
        is StateData.Success -> StateData.Success(mapper(data))
    }
}

suspend fun <T : Any> FlowCollector<StateData<T>>.emitState(
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

fun <T> Flow<StateData<T>>.mapFailedState(mapper: suspend (StateData.Failed<T>) -> StateData.Failed<T>) =
    map {
        if (it.isStateFailed()) mapper(it)
        else it
    }

@OptIn(ExperimentalContracts::class)
fun <T> StateData<T>.isStateSucceed(): Boolean {
    contract {
        returns(true) implies (this@isStateSucceed is StateData.Success)
    }
    return this@isStateSucceed is StateData.Success
}

@OptIn(ExperimentalContracts::class)
fun <T> StateData<T>.isStateFailed(): Boolean {
    contract {
        returns(true) implies (this@isStateFailed is StateData.Failed)
    }
    return this@isStateFailed is StateData.Failed
}