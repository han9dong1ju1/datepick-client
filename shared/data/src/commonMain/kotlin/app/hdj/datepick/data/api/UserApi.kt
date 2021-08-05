package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.data.request.UserProfileRequest
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*

@Singleton
open class UserApi @Inject constructor(override val client: HttpClient) : Api {

    override val basePath: String = "/api/v1/users"

    suspend fun getMe() : ApiResponse<UserResponse> =
        get("me")

    suspend fun updateMe(userProfileRequest: UserProfileRequest) : ApiResponse<UserResponse> =
        post("me") { body = userProfileRequest }

    suspend fun register(userProfileRequest: UserProfileRequest) : ApiResponse<UserResponse> =
        post("me/register")

    suspend fun unregister(): ApiResponse<String> =
        delete("me")

}