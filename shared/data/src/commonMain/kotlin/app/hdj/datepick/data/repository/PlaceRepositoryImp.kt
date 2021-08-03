package app.hdj.datepick.data.repository

import app.hdj.datepick.data.api.PlaceApi
import app.hdj.datepick.data.db.PlaceCache
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.Place
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class PlaceRepositoryImp @Inject constructor(
    private val placeApi: PlaceApi,
    private val placeCache: PlaceCache
) : PlaceRepository {

    override fun getById(id: String): Flow<StateData<Place>> {
        TODO("Not yet implemented")
    }

}