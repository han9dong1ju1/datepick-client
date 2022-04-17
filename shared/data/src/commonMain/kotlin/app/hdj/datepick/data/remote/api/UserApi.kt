package app.hdj.datepick.data.remote.api

import app.hdj.datepick.data.model.request.user.UserProfileRequest
import app.hdj.datepick.data.model.request.user.UserRegisterRequest
import app.hdj.datepick.data.model.request.user.UserUnregisterRequest
import app.hdj.datepick.data.model.response.user.UserResponse
import app.hdj.datepick.data.remote.*
import app.hdj.datepick.data.utils.MockResponses
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.delay

fun fakeUserApi() = object : UserApi {
    override val client: HttpClient
        get() = TODO("Not yet implemented")

    override suspend fun getMe(): ApiResponse<UserResponse> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.user(),
            error = null,
            message = null
        )
    }

    override suspend fun updateMe(userProfileRequest: UserProfileRequest): ApiResponse<UserResponse> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.user(),
            error = null,
            message = null
        )
    }
}

interface UserApi : Api {
    override val basePath: String get() = "/v1/users/"

    suspend fun getMe(): ApiResponse<UserResponse>
    suspend fun updateMe(userProfileRequest: UserProfileRequest): ApiResponse<UserResponse>

}

@Singleton
open class UserApiImp @Inject constructor(override val client: HttpClient) : UserApi {

    override suspend fun getMe(): ApiResponse<UserResponse> =
        get("me")

    override suspend fun updateMe(
        userProfileRequest: UserProfileRequest
    ): ApiResponse<UserResponse> {

        return patch("me") {
            val (nickname, gender, image) = userProfileRequest
            parameter("remove_image", false)

            setBody(
                MultiPartFormDataContent(
                    formData {
                        append("nickname", nickname)
                        gender?.let { append("gender", it.name) }
                        image?.let {
                            appendInput(
                                key = "image",
                                headers =
                                headersOf(
                                    HttpHeaders.ContentType to listOf(ContentType.Image.Any.toString()),
                                )
                            ) { it }
                        }
                    }
                )
            )
        }
    }
}