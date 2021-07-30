package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.api.PlaceApi
import app.hdj.shared.client.data.cache.PlaceCache
import app.hdj.shared.client.data.paging.*
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Place
import app.hdj.shared.client.domain.repo.PlaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

open class PlaceRepositoryImp(
    private val placeCache: PlaceCache,
    private val placeApi: PlaceApi
) : PlaceRepository {

    @OptIn(ExperimentalTime::class)
    override fun getPlace(placeId: String) = TODO()

    override fun queryPlace(placeQuery: PlaceQuery): Flow<PlatformPagingData<Place>> =
        createPagingSource<Int, Place> {
            placeApi.runCatching {
                val (search, sort) = placeQuery
                queryPlace(search = search, sort = sort, page = it)
            }.fold({
                val response = it.data
                PageData.Success(response.key, response.data)
            }, {
                PageData.Failed(it)
            })
        }.asPager(20)


    override fun likePlace(placeId: String): Flow<StateData<Place>> {
        TODO("Not yet implemented")
    }

}