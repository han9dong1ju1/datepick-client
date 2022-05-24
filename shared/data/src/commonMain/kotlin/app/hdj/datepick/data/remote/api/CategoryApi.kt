package app.hdj.datepick.data.remote.api

import app.hdj.datepick.data.model.response.place.PlaceCategoryResponse
import app.hdj.datepick.data.remote.Api
import app.hdj.datepick.data.remote.ApiResponse
import app.hdj.datepick.data.remote.get
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton


interface CategoryApi : Api {
    override val basePath: String get() = "/v1/categories/"

    suspend fun getCategories(): ApiResponse<List<PlaceCategoryResponse>>

}

@Singleton
class CategoryApiImp @Inject constructor() : CategoryApi {

    override suspend fun getCategories(): ApiResponse<List<PlaceCategoryResponse>> = get()

}