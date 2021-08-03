package app.hdj.datepick.domain

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