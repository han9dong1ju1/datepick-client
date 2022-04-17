@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.KakaoPlaceSearch
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.params.KakaoPlaceSearchQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import com.kuuurt.paging.multiplatform.Pager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getById(id: Long): Flow<LoadState<Place>>

    fun queryPagedPlaces(params: PlaceQueryParams): Pager<Long, Place>

    fun queryFirstPagePlaces(params: PlaceQueryParams): Flow<LoadState<List<Place>>>

    fun queryPlacesFromCourse(courseId: Long): Flow<LoadState<List<Place>>>

    fun queryFromKakao(params: KakaoPlaceSearchQueryParams): Flow<LoadState<List<KakaoPlaceSearch.Document>>>

}