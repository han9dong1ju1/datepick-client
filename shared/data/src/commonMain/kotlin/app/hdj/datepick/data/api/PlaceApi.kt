package app.hdj.datepick.data.api

import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*

@Singleton
class PlaceApi @Inject constructor(override val client: HttpClient) : Api {

    override val basePath: String get() = "/place"



}