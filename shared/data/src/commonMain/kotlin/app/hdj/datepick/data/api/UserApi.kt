package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.user.UserData
import app.hdj.datepick.data.request.user.UserProfileRequest
import app.hdj.datepick.data.request.user.UserRegisterRequest
import app.hdj.datepick.data.request.user.UserUnregisterRequest
import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

interface UserApi : Api {
    override val basePath: String get() = "/api/v1/users"

    suspend fun getMe(): ApiResponse<UserData>
    suspend fun updateMe(userProfileRequest: UserProfileRequest): ApiResponse<UserData>

    suspend fun register(userRegisterRequest: UserRegisterRequest): ApiResponse<UserData>
    suspend fun unregister(userUnregisterRequest: UserUnregisterRequest): ApiResponse<String?>
}

@Singleton
open class UserApiImp @Inject constructor(
    private val authenticator: Authenticator,
    override val client: HttpClient
) : UserApi {

    override suspend fun getMe(): ApiResponse<UserData> =
        get("me")

    override suspend fun updateMe(
        userProfileRequest: UserProfileRequest
    ): ApiResponse<UserData> {

        val uid = authenticator.getCurrentFirebaseUser()?.uid ?: "me"

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
                                    HttpHeaders.ContentDisposition to listOf("filename=$uid.jpg")
                                )
                            ) { it }
                        }
                    }
                )
            )
        }
    }

    override suspend fun register(userRegisterRequest: UserRegisterRequest): ApiResponse<UserData> =
        post("register") { setBody(userRegisterRequest) }

    override suspend fun unregister(userUnregisterRequest: UserUnregisterRequest): ApiResponse<String?> =
        post("unregister") { setBody(userUnregisterRequest) }

}