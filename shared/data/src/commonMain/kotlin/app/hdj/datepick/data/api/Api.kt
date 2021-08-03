package app.hdj.datepick.data.api

import io.ktor.client.*

interface Api {

    val client: HttpClient

    val basePath : String

}