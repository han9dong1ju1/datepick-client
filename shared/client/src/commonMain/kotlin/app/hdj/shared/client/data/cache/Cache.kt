package app.hdj.shared.client.data.cache

interface Cache<Data> {

    suspend fun get(id : String) : Data?

    suspend fun cache(data: Data)

}