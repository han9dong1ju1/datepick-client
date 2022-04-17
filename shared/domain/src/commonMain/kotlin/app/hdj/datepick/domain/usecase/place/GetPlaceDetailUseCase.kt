package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class GetPlaceDetailUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    operator fun invoke(input: Long) = placeRepository.getById(input)

}