package app.hdj.datepick.data.repository

import app.hdj.datepick.data.mapper.KakaoPlaceMapper
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.mapper.PlaceMapper
import app.hdj.datepick.data.model.response.place.PlaceResponse
import app.hdj.datepick.data.remote.PagingResponse
import app.hdj.datepick.data.remote.api.KakaoPlaceSearchApi
import app.hdj.datepick.data.remote.api.PlaceApi
import app.hdj.datepick.data.remote.map
import app.hdj.datepick.data.utils.MockResponses
import app.hdj.datepick.data.utils.createPager
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.failed
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.LoadState.Companion.success
import app.hdj.datepick.domain.model.place.KakaoPlaceSearch
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.domain.usecase.place.params.KakaoPlaceSearchQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.domain.usecase.place.params.filterParams
import app.hdj.datepick.domain.usecase.place.params.placeQueryParams
import app.hdj.datepick.utils.PlatformLogger
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import com.kuuurt.paging.multiplatform.PagingData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class PlaceRepositoryImp @Inject constructor(
    private val kakaoPlaceSearchApi: KakaoPlaceSearchApi,
    private val api: PlaceApi
) : PlaceRepository,
    Mapper<PlaceResponse, Place> by PlaceMapper {

    override fun getById(id: Long) = flow<LoadState<Place>> {
        emit(loading())
        api.runCatching { getById(id) }
            .onFailure { emit(failed(it)) }
            .onSuccess { emit(success(it.data.dataToDomain())) }
    }

    override fun queryPagedPlaces(params: PlaceQueryParams) = createPager { page ->
//        val pagingResponse = api.queryPlaces(page, params).data
//        pagingResponse.map { it.dataToDomain() }
        delay(2000)
        PagingResponse(
            count = 1000,
            currentPage = page,
            isLastPage = false,
            content = (0..20).map { MockResponses.place() }
        ).map { it.dataToDomain() }
    }

    override fun queryFirstPagePlaces(params: PlaceQueryParams) = flow<LoadState<List<Place>>> {
        emit(loading())
        delay(2000)
        emit(success((0..9).map { MockResponses.place().dataToDomain() }))
//        api.runCatching { queryPlaces(0, params).data.content }
//            .onFailure { emit(failed(it)) }
//            .onSuccess { emit(success(it.mapDomain())) }
    }

    override fun queryPlacesFromCourse(courseId: Long) = flow<LoadState<List<Place>>> {
        emit(loading())
        delay(2000)
        emit(success((0..5).map { MockResponses.place().dataToDomain() }))
//        api.runCatching { queryPlaces(0, placeQueryParams { filterParams { this.courseId = courseId } }).data.content }
//            .onFailure { emit(failed(it)) }
//            .onSuccess { emit(success(it.mapDomain())) }
    }

    override fun queryFromKakao(params: KakaoPlaceSearchQueryParams) =
        flow<LoadState<List<KakaoPlaceSearch.Document>>> {
            emit(loading())
            with(KakaoPlaceMapper) {
                kakaoPlaceSearchApi
                    .runCatching { search(params) }
                    .onFailure { emit(failed(it)) }
                    .onSuccess { emit(success(it.dataToDomain().documents)) }
            }
        }

}