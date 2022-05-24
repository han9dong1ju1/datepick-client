@file:OptIn(ExperimentalSettingsApi::class)

package app.hdj.datepick.data.di

import app.hdj.datepick.data.remote.api.*
import app.hdj.datepick.data.remote.client.DatepickApiHttpClient
import app.hdj.datepick.data.remote.client.KakaoApiHttpClient
import app.hdj.datepick.data.repository.FeaturedRepositoryImp
import app.hdj.datepick.data.storage.settings.AppSettingsImp
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.domain.settings.AppSettings
import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.toFlowSettings
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

private val Scope.datepickApiClient get() = get<HttpClient>(named("datepick"))
private val Scope.kakaoApiClient get() = get<HttpClient>(named("kakao"))

val dataModule = module {
    single { AppleSettings(NSUserDefaults()).toFlowSettings() }
    single<AppSettings> { AppSettingsImp(get()) }
    single(qualifier = named("kakao")) { KakaoApiHttpClient(Darwin, get()) }

    single<UserApi> { UserApiImp() }
    single<FeaturedApi> { FeaturedApiImp() }
    single<PlaceApi> { PlaceApiImp(datepickApiClient) }
    single<KakaoPlaceSearchApi> { KakaoPlaceSearchApiImp(kakaoApiClient) }

    single<FeaturedRepository> { FeaturedRepositoryImp(get()) }
}