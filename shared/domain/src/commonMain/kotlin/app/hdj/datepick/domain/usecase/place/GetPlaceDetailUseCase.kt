package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class GetPlaceDetailUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) : UseCase<Long, Flow<LoadState<Place>>> {

    override fun invoke(input: Long) = placeRepository.getById(input)

}