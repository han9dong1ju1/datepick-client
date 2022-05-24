package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class GetPlaceCategoriesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    operator fun invoke() = placeRepository.getPlaceCategories()

}