package app.hdj.datepick.data.remote.client

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual fun getClient(): HttpClient = DatepickApiHttpClient(OkHttp)