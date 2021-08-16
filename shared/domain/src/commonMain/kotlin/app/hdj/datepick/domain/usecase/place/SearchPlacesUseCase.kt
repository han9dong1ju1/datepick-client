package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class SearchPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    fun execute(param: Param): Flow<LoadState<List<Place>>> {
        val (query, sort) = param
        return placeRepository.search(query, sort.value)
    }

    data class Param(
        val query: String,
        val sort: Sort
    ) {
        enum class Sort(val value: String) {
            RATING("rating"),
            DISTANCE("distance")
        }
    }

}