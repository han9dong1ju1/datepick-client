package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getById(id : Long) : Flow<LoadState<Place>>

    fun search(params : PlaceQueryParams) : Flow<LoadState<List<Place>>>

}