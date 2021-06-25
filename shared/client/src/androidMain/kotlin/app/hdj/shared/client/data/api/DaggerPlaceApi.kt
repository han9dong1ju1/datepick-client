package app.hdj.shared.client.data.api

import io.ktor.client.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerPlaceApi @Inject constructor(client: HttpClient) : PlaceApi(client)