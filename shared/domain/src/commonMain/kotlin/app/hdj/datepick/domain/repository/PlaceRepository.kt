package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.place.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getById(id : String) : Flow<StateData<Place>>

}