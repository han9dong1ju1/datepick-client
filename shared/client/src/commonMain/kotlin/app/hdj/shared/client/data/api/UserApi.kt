package app.hdj.shared.client.data.api

import app.hdj.shared.client.data.ApiResponse
import app.hdj.shared.client.domain.entity.User
import io.ktor.client.*
import io.ktor.client.request.*

open class UserApi(val client: HttpClient) {

    suspend fun getUser(userId : String) = client.get<ApiResponse<User>> {
        parameter("userId", userId)
    }

}