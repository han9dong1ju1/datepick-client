@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.model.place.KakaoPlaceSearch
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.params.KakaoPlaceSearchQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import com.kuuurt.paging.multiplatform.Pager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getById(id: Long): Flow<Place>

    fun queryPagedPlaces(params: PlaceQueryParams): Pager<Long, Place>

    fun queryFirstPagePlaces(params: PlaceQueryParams): Flow<List<Place>>

    fun queryPlacesFromCourse(courseId: Long): Flow<List<Place>>

    fun queryFromKakao(params: KakaoPlaceSearchQueryParams): Flow<List<KakaoPlaceSearch.Document>>

    fun addPlace(kakaoPlace : KakaoPlaceSearch.Document): Flow<Place>

}