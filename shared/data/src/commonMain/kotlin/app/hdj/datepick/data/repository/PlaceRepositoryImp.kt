package app.hdj.datepick.data.repository

import app.hdj.datepick.PlaceEntity
import app.hdj.datepick.data.api.PlaceApi
import app.hdj.datepick.data.datastore.PlaceDataStore
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.mapper.PlaceMapper
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.emitState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.flow
import kotlin.time.ExperimentalTime

@Singleton
@OptIn(ExperimentalTime::class)
class PlaceRepositoryImp @Inject constructor(
    private val api: PlaceApi,
    private val datastore: PlaceDataStore
) : PlaceRepository, Mapper<PlaceEntity, Place> by PlaceMapper {

    override fun getById(id: Long) = flow {
        emitState {
            val cached = datastore.getById(id)
            if (cached != null && cached.cachedAt isPassedDay 30) cached.asDomain()
            else {
                val place = api.getById(id).data
                datastore.save(requireNotNull(place).asTable())
                place
            }
        }
    }

    override fun search(query: String, sort: String) = flow<LoadState<List<Place>>> {
        emitState {
            val places = requireNotNull(api.search(query, sort).data)
            datastore.saveAll(places = places.mapTable())
            places
        }
    }

}