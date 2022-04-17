package app.hdj.datepick.data.remote

import app.hdj.datepick.domain.LoadState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("message") val message: String?,
    @SerialName("error") val error: String?,
    @SerialName("data") val data: T
)

suspend fun <T, R> R.responseToLoadState(call: suspend R.() -> ApiResponse<T>): LoadState<T> {
    return call
        .runCatching { invoke(this@responseToLoadState) }
        .fold({
            if (it.error == null) LoadState.success(it.data)
            else LoadState.failed(Exception(it.message))
        }, {
            LoadState.failed(it)
        })
}