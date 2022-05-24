package app.hdj.datepick.data.repository

import app.hdj.datepick.data.mapper.KakaoPlaceMapper
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.mapper.PlaceMapper
import app.hdj.datepick.data.model.request.place.PlaceAddRequest
import app.hdj.datepick.data.model.response.place.PlaceResponse
import app.hdj.datepick.data.remote.api.CategoryApi
import app.hdj.datepick.data.remote.api.KakaoPlaceSearchApi
import app.hdj.datepick.data.remote.api.PlaceApi
import app.hdj.datepick.data.remote.map
import app.hdj.datepick.data.utils.createPager
import app.hdj.datepick.domain.model.place.KakaoPlaceSearch
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.domain.usecase.place.params.KakaoPlaceSearchQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.domain.usecase.place.params.filterParams
import app.hdj.datepick.domain.usecase.place.params.placeQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class PlaceRepositoryImp @Inject constructor(
    private val kakaoPlaceSearchApi: KakaoPlaceSearchApi,
    @Named("real") private val api: PlaceApi,
    @Named("real") private val categoryApi: CategoryApi,
) : PlaceRepository,
    Mapper<PlaceResponse, Place> by PlaceMapper {

    override fun getById(id: Long) = flow {
        val response = api.getById(id)
        emit(response.data.asDomain())
    }

    private var memoryCachedCategories = listOf<Place.Category>()

    override fun getPlaceCategories(): Flow<List<Place.Category>> = flow {
        if (memoryCachedCategories.isEmpty()) {
            val response = categoryApi.getCategories()
            memoryCachedCategories = response.data.map { Place.Category(it.id, it.name) }
        }
        emit(memoryCachedCategories)
    }

    override fun queryPagedPlaces(params: PlaceQueryParams) = createPager { page ->
        val pagingResponse = api.queryPlaces(page, params).data
        pagingResponse.map { it.asDomain() }
    }

    override fun queryFirstPagePlaces(params: PlaceQueryParams) = flow {
        val response = api.queryPlaces(0, params)
        emit(response.data.content.mapDomain())
    }

    override fun queryPlacesFromCourse(courseId: Long) = flow {
        val queryParams = placeQueryParams {
            filterParams { this.courseId = courseId }
        }
        val response = api.queryPlaces(0, queryParams)
        emit(response.data.content.mapDomain())
    }

    override fun queryFromKakao(params: KakaoPlaceSearchQueryParams) =
        flow {
            with(KakaoPlaceMapper) {
                val response = kakaoPlaceSearchApi.search(params)
                emit(response.asDomain().documents)
            }
        }

    override fun addPlace(kakaoPlace: KakaoPlaceSearch.Document) = flow {
        val response = api.addPlace(
            PlaceAddRequest(
                kakaoId = kakaoPlace.id,
                name = kakaoPlace.placeName,
                address = kakaoPlace.addressName,
                latitude = kakaoPlace.latLng.latitude,
                longitude = kakaoPlace.latLng.longitude,
                categories = kakaoPlace.categoryName
            )
        )
        emit(response.data.asDomain())
    }

}