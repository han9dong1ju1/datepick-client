package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingData
import kotlinx.coroutines.flow.Flow

@Singleton
class SearchPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) : UseCase<PlaceQueryParams, Pager<Long, Place>> {

    override fun invoke(input: PlaceQueryParams) = placeRepository.queryPagedPlaces(input)

}