package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.model.place.KakaoPlaceSearch
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class AddPlaceUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    operator fun invoke(kakaoPlace: KakaoPlaceSearch.Document) =
        placeRepository.addPlace(kakaoPlace)
}