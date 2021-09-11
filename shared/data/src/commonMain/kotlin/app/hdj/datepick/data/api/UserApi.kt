package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.data.request.UserProfileRequest
import app.hdj.datepick.data.request.UserUnregisterRequest
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*

interface UserApi : Api {
    override val basePath: String get() = "/api/v1/users"

    suspend fun getMe(): ApiResponse<UserResponse>
    suspend fun signIn(): ApiResponse<UserResponse>
    suspend fun signOut(): ApiResponse<String>
    suspend fun updateMe(userProfileRequest: UserProfileRequest): ApiResponse<UserResponse>
    suspend fun register(userProfileRequest: UserProfileRequest): ApiResponse<UserResponse>
    suspend fun unregister(userUnregisterRequest: UserUnregisterRequest): ApiResponse<String>
}

@Singleton
open class UserApiImp @Inject constructor(override val client: HttpClient) : UserApi {

    override suspend fun getMe(): ApiResponse<UserResponse> =
        get("me")

    override suspend fun signIn(): ApiResponse<UserResponse> =
        get("sign-in")

    override suspend fun signOut(): ApiResponse<String> =
        get("sign-out")

    override suspend fun updateMe(userProfileRequest: UserProfileRequest): ApiResponse<UserResponse> =
        patch { body = userProfileRequest }

    override suspend fun register(userProfileRequest: UserProfileRequest): ApiResponse<UserResponse> =
        post { body = userProfileRequest }

    override suspend fun unregister(userUnregisterRequest: UserUnregisterRequest): ApiResponse<String> =
        post("unregister") { body = userUnregisterRequest }

}