package app.hdj.datepick.data.di

import app.hdj.datepick.DatePickDatabase
import app.hdj.datepick.data.api.*
import app.hdj.datepick.domain.*
import app.hdj.datepick.data.datastore.FeaturedDataStore
import app.hdj.datepick.data.datastore.FeaturedDataStoreImp
import app.hdj.datepick.data.repository.FeaturedRepositoryImp
import app.hdj.datepick.data.utils.FirebaseAuthenticator
import app.hdj.datepick.domain.repository.FeaturedRepository
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import org.koin.dsl.module

val dataModule = module {
    single { DatePickHttpClient(Darwin, get(), get()) }

    single<UserApi> { UserApiImp(get(), get()) }
    single<FeaturedApi> { FeaturedApiImp(get()) }
    single<PlaceApi> { PlaceApiImp(get()) }
    single<FeaturedRepository> { FeaturedRepositoryImp(get(), get()) }
    single<FeaturedDataStore> { FeaturedDataStoreImp(get()) }
    single<Authenticator> { FirebaseAuthenticator() }

    single { DatePickDatabase(NativeSqliteDriver(DatePickDatabase.Schema, name = "datepick-database.db")) }
    single { get<DatePickDatabase>().featuredEntityQueries }
    single { get<DatePickDatabase>().courseEntityQueries }
    single { get<DatePickDatabase>().placeEntityQueries }
}