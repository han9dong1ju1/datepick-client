package app.hdj.datepick.data.api

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*
import io.ktor.client.request.*

@Singleton
class UserApi @Inject constructor(override val client: HttpClient) : Api {

    override val basePath: String = "api/v1/users/"

    suspend fun getMe(): User = client.get(basePath)

}