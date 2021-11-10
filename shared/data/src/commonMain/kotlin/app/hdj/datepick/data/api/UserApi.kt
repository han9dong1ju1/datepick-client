package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.data.request.UserProfileRequest
import app.hdj.datepick.data.request.UserRegisterRequest
import app.hdj.datepick.data.request.UserUnregisterRequest
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*
import io.ktor.client.request.forms.*

interface UserApi : Api {
    override val basePath: String get() = "/api/v1/users"

    suspend fun getMe(): ApiResponse<UserResponse>
    suspend fun updateMe(userProfileRequest: UserProfileRequest): ApiResponse<UserResponse>
    suspend fun register(userRegisterRequest: UserRegisterRequest): ApiResponse<String?>
    suspend fun unregister(userUnregisterRequest: UserUnregisterRequest): ApiResponse<String>
}

@Singleton
open class UserApiImp @Inject constructor(override val client: HttpClient) : UserApi {

    override suspend fun getMe(): ApiResponse<UserResponse> =
        get("me")

    override suspend fun updateMe(userProfileRequest: UserProfileRequest): ApiResponse<UserResponse> =
        patch { body = userProfileRequest }

    override suspend fun register(userRegisterRequest: UserRegisterRequest): ApiResponse<String?> =
        post("register") { body = userRegisterRequest }

    override suspend fun unregister(userUnregisterRequest: UserUnregisterRequest): ApiResponse<String> =
        post("unregister") { body = userUnregisterRequest }

}