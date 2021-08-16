package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getById(id : Long) : Flow<LoadState<Place>>

    fun search(query : String, sort : String) : Flow<LoadState<List<Place>>>

}