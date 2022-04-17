package app.hdj.datepick.data.remote.api

import app.hdj.datepick.data.model.response.district.DistrictResponse
import app.hdj.datepick.data.remote.Api
import app.hdj.datepick.data.remote.ApiResponse
import app.hdj.datepick.data.remote.get
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import kotlinx.coroutines.delay
import kotlin.random.Random

fun fakeDistrictApi(): DistrictApi = object : DistrictApi {
    override val client: HttpClient
        get() = TODO("Not yet implemented")

    override suspend fun getDistricts(): ApiResponse<List<DistrictResponse>> {
        delay(1000)
        return ApiResponse(
            data = listOf(
                "연남/홍대입구",
                "합정/망원",
                "안국/삼청",
                "강남/서초",
                "잠실/선릉",
            ).map {
                DistrictResponse(
                    Random.nextLong(),
                    it,
                    latitude = Random.nextDouble(37.5, 37.7),
                    longitude = Random.nextDouble(127.5, 127.7)
                )
            },
            error = null,
            message = null
        )
    }
}

interface DistrictApi : Api {
    override val basePath: String get() = "/v1/district/"

    suspend fun getDistricts(): ApiResponse<List<DistrictResponse>>

}

@Singleton
class DistrictApiImp @Inject constructor(override val client: HttpClient) : DistrictApi {

    override suspend fun getDistricts(): ApiResponse<List<DistrictResponse>> = get()

}