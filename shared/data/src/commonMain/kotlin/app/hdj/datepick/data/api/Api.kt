package app.hdj.datepick.data.api

import io.ktor.client.*
import io.ktor.client.request.*

interface Api {

    val client: HttpClient

    val basePath: String

}

suspend inline fun <reified T> Api.get(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
) = client.get<T> {
    url { encodedPath = "$basePath/$suffix" }
    block()
}

suspend inline fun <reified T> Api.post(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
) = client.post<T> {
    url { encodedPath = "$basePath/$suffix" }
    block()
}

suspend inline fun <reified T> Api.delete(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
) = client.delete<T> {
    url { encodedPath = "$basePath/$suffix" }
    block()
}

suspend inline fun <reified T> Api.patch(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
) = client.patch<T> {
    url { encodedPath = "$basePath/$suffix" }
    block()
}