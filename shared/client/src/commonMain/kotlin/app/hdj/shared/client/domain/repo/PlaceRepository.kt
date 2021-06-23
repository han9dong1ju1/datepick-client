package app.hdj.shared.client.domain.repo

import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getPlace(placeId : String) : Flow<StateData<Place>>

    fun queryPlace() : Flow<StateData<List<Place>>>

    fun likePlace(placeId: String)

}