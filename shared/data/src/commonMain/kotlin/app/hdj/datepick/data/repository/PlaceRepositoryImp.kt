package app.hdj.datepick.data.repository

import app.hdj.datepick.PlaceEntity
import app.hdj.datepick.data.api.PlaceApi
import app.hdj.datepick.data.datastore.PlaceDataStore
import app.hdj.datepick.data.entity.place.PlaceResponse
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.mapper.PlaceMapper
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.emitState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import app.hdj.datepick.utils.date.isPassedDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class PlaceRepositoryImp @Inject constructor(
    private val api: PlaceApi,
    private val dataStore: PlaceDataStore
) : PlaceRepository, Mapper<PlaceEntity, PlaceResponse, Place> by PlaceMapper {

    override fun getById(id: Long) = flow {
        val cached = dataStore.runCatching { get(id) }.getOrNull()
        emitState(cached?.tableToDomain()) {
            if (cached != null && cached.cachedAt isPassedDay 30) cached.tableToDomain()
            else api.getById(id).data.dataToDomain()
        }.onSuccess {
            dataStore.save(it.domainToTable())
        }
    }

    override fun search(params: PlaceQueryParams): Flow<LoadState<List<Place>>> {
        TODO("Not yet implemented")
    }
}