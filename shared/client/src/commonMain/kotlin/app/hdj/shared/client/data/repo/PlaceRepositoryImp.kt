package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.api.PlaceApi
import app.hdj.shared.client.data.cache.PlaceCache
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
    override fun getPlace(placeId: String) = flow {

        val cached = placeCache.get(placeId)

        val currentTime = Clock.System.now()

        val isCacheExpired =
            cached != null && cached.cacheExpireAt >= currentTime.toEpochMilliseconds()

        if (isCacheExpired) {
            emit(StateData.Success(requireNotNull(cached)))
        } else {
            emit(StateData.Loading())
            placeApi
                .runCatching { getPlace(placeId) }
                .onSuccess {

                    val data = it.data
                    data.cacheExpireAt = (currentTime + Duration.days(3)).toEpochMilliseconds()
                    placeCache.cache(data)

                    emit(StateData.Success(data))
                }
                .onFailure {
                    emit(StateData.Failed(cached, it))
                }
        }

    }

    override fun queryPlace(): Flow<StateData<List<Place>>> {
        TODO("Not yet implemented")
    }

    override fun likePlace(placeId: String): Flow<StateData<Place>> {
        TODO("Not yet implemented")
    }

}