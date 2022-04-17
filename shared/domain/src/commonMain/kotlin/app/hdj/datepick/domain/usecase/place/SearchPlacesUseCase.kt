package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class SearchPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    operator fun invoke(input: PlaceQueryParams) = placeRepository.queryPagedPlaces(input)

}