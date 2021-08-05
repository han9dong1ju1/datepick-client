package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.data.request.UserProfileRequest
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*

@Singleton
class UserApi @Inject constructor(override val client: HttpClient) : Api {

    override val basePath: String = "/api/v1/users"

    suspend fun getMe() : UserResponse =
        get("me")

    suspend fun updateMe(userProfileRequest: UserProfileRequest) : UserResponse =
        delete("me") { body = userProfileRequest }

    suspend fun unregister(): Unit =
        delete("me")

}