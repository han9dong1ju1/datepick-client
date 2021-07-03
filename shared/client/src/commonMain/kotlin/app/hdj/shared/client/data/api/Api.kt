package app.hdj.shared.client.data.api

import io.ktor.client.*

interface Api {

    val client : HttpClient

    val baseRequest : HttpClient

}