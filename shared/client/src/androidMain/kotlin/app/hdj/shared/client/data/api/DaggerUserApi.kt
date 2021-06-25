package app.hdj.shared.client.data.api

import io.ktor.client.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerUserApi @Inject constructor(client: HttpClient) : UserApi(client)