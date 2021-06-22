package app.hdj.shared.client.domain

sealed interface State<Data> {

    class Loading<Data> : State<Data>

    class Failed<Data>(val throwable: Throwable?) : State<Data>

    data class Success<Data>(val data : Data) : State<Data>

}