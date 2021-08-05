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
    val MeDataStoreImp.meDataStore : MeDataStore

    @get:[Binds]
    val PlaceDataStoreImp.placeDataStore : PlaceDataStore

    @get:[Binds]
    val CourseDataStoreImp.courseDataStore : CourseDataStore

}