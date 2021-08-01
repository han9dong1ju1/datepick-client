package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getPlace(id: String): Flow<Place>

}