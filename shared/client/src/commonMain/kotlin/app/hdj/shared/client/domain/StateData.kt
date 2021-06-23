package app.hdj.shared.client.domain

sealed interface StateData<Data> {

    class Loading<Data> : StateData<Data>

    class Failed<Data>(val cachedData : Data? = null, val throwable: Throwable?) : StateData<Data>

    data class Success<Data>(val data : Data) : StateData<Data>

}