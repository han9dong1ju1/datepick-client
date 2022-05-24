package app.hdj.datepick.data.remote.client

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual fun getClient(): HttpClient = DatepickApiHttpClient(Darwin)