package app.hdj.datepick.data.remote

import app.hdj.datepick.data.remote.client.getClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

interface Api {

    val basePath: String

}

//suspend inline fun <reified T> Api.get(
//    suffix: String = "",
//    block: HttpRequestBuilder.() -> Unit = {}
//) = client.get<T> {
//
//    url { encodedPath = "$basePath$suffix" }
//    block()
//}

suspend inline fun <reified T> Api.get(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
): T {
    return getClient().get("$basePath$suffix") {
        block()
    }.body()
}

suspend inline fun <reified T> Api.post(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
): T {
    return getClient().post("$basePath$suffix") {
        block()
    }.body()
}

suspend inline fun <reified T> Api.delete(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
): T {
    return getClient().delete("$basePath$suffix") {
        block()
    }.body()
}

suspend inline fun <reified T> Api.patch(
    suffix: String = "",
    block: HttpRequestBuilder.() -> Unit = {}
): T {
    return getClient().patch("$basePath$suffix") {
        block()
    }.body()
}