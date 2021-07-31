package app.hdj.shared.client.domain

sealed interface StateData<Data> {

    class Loading<Data> : StateData<Data>

    class Failed<Data>(val cachedData: Data? = null, val throwable: Throwable? = null) :
        StateData<Data> {
        init {
            throwable?.printStackTrace()
        }
    }

    data class Success<Data>(val data: Data) : StateData<Data>

    fun <R> mapBy(mapper: (Data) -> R): StateData<R> = when (this) {
        is Loading<Data> -> Loading()
        is Success<Data> -> Success(mapper(this.data))
        is Failed<Data> -> Failed(this.cachedData?.let { mapper(it) }, this.throwable)
    }

}