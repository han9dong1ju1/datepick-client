package app.hdj.shared.client.domain.repo

import app.hdj.shared.client.data.paging.PageData
import app.hdj.shared.client.data.paging.PlatformPagingData
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getPlace(placeId: String): Flow<StateData<Place>>

    fun likePlace(placeId: String): Flow<StateData<Place>>

    fun queryPlace(placeQuery: PlaceQuery): Flow<PlatformPagingData<Place>>

}