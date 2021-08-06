@file:Suppress("IllegalIdentifier", "NonAsciiCharacters", "SpellCheckingInspection")

package app.hdj.datepick.data

import app.hdj.datepick.data.api.ApiResponse
import app.hdj.datepick.data.api.UserApi
import app.hdj.datepick.utils.exception.ApiResponseException
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class DatePickHttpClientTest {

    @Test
    fun `만약 404 에러의 응답이라면, API 의 에러가 ApiResponseException 이어야하고, 코드가 404여야한다`() =
        runBlocking<Unit> {
            val errorResponse = ApiResponse("Not Found", 404, null)

            val api = UserApi(TestDatePickHttpClient(customApiResponse = errorResponse))

            api.runCatching {
                getMe()
            }.onSuccess {
                println(it)
                assertTrue(false)
            }.onFailure {
                it.printStackTrace()
                assertTrue(it is ApiResponseException && it.code == 404)
            }
        }

}