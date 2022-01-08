package app.hdj.datepick.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

interface Api {

    val client: HttpClient

    val basePath: String

}

//suspend inline fun <reified T> Api.get(
//    suffix: String = "",
//    block: HttpRequestBuilder.() -> Unit = {}
//) = client.get<T> {
//
//    url { encodedPath = "$basePath/$suffix" }
//    block()
//}

suspend inline fun <reified T> Api.get(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
) = client.get {
    url { encodedPath = "$basePath/$suffix" }
    block()
}.body<T>()

suspend inline fun <reified T> Api.post(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
) = client.post {
    url { encodedPath = "$basePath/$suffix" }
    block()
}.body<T>()

suspend inline fun <reified T> Api.delete(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
) = client.delete {
    url { encodedPath = "$basePath/$suffix" }
    block()
}.body<T>()

suspend inline fun <reified T> Api.patch(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
) = client.patch {
    url { encodedPath = "$basePath/$suffix" }
    block()
}.body<T>()