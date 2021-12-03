package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.user.UserResponse
import app.hdj.datepick.data.request.user.UserProfileRequest
import app.hdj.datepick.data.request.user.UserRegisterRequest
import app.hdj.datepick.data.request.user.UserUnregisterRequest
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

interface UserApi : Api {
    override val basePath: String get() = "/api/v1/users"

    suspend fun getMe(): ApiResponse<UserResponse>
    suspend fun updateMe(
        myId: Long,
        userProfileRequest: UserProfileRequest
    ): ApiResponse<UserResponse>

    suspend fun register(userRegisterRequest: UserRegisterRequest): ApiResponse<UserResponse>
    suspend fun unregister(userUnregisterRequest: UserUnregisterRequest): ApiResponse<String?>
}

@Singleton
open class UserApiImp @Inject constructor(override val client: HttpClient) : UserApi {

    override suspend fun getMe(): ApiResponse<UserResponse> =
        get("me")

    override suspend fun updateMe(
        myId: Long,
        userProfileRequest: UserProfileRequest
    ): ApiResponse<UserResponse> =
        patch("$myId") {
            val (nickname, gender, image) = userProfileRequest

            parameter("removePhoto", false)

            body = MultiPartFormDataContent(
                formData {
                    append("nickname", nickname)
                    append("gender", gender.name)
                    image?.let {
                        appendInput(
                            key = "image",
                            headers =
                            headersOf(
                                HttpHeaders.ContentType to listOf(ContentType.Image.Any.toString()),
                                HttpHeaders.ContentDisposition to listOf("filename=$myId.jpg")
                            )
                        ) { it }
                    }
                }
            )
        }

    override suspend fun register(userRegisterRequest: UserRegisterRequest): ApiResponse<UserResponse> =
        post("register") { body = userRegisterRequest }

    override suspend fun unregister(userUnregisterRequest: UserUnregisterRequest): ApiResponse<String?> =
        post("unregister") { body = userUnregisterRequest }

}