package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class GetPlaceByIdUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    fun execute(id: Long) = placeRepository.getById(id)

}