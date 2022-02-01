package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class GetNearbyPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) : UseCase<PlaceQueryParams, Flow<LoadState<List<Place>>>> {

    override fun invoke(input: PlaceQueryParams): Flow<LoadState<List<Place>>> {
        return placeRepository.search(input)
    }

}