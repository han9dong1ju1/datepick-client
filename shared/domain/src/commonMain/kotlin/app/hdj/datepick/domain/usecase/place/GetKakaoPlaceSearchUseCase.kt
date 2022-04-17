package app.hdj.datepick.domain.usecase.place

import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.domain.usecase.place.params.KakaoPlaceSearchQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class GetKakaoPlaceSearchUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    operator fun invoke(kakaoPlaceSearchQueryParams: KakaoPlaceSearchQueryParams) =
        placeRepository.queryFromKakao(kakaoPlaceSearchQueryParams)

}