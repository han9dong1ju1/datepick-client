package app.hdj.datepick.data.di

import app.hdj.datepick.data.datastore.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {

    @get:[Binds]
    val MeDataStoreImp.dataStore : MeDataStore

    @get:[Binds]
    val PlaceDataStoreImp.dataStore : PlaceDataStore

    @get:[Binds]
    val CourseDataStoreImp.dataStore : CourseDataStore

}