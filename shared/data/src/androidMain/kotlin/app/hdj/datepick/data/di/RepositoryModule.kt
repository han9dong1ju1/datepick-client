package app.hdj.datepick.data.di

import app.hdj.datepick.data.repository.*
import app.hdj.datepick.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @get:[Binds]
    val PlaceRepositoryImp.providePlaceRepository : PlaceRepository

    @get:[Binds]
    val CourseRepositoryImp.provideCourseRepository : CourseRepository

    @get:[Binds]
    val MeRepositoryImp.provideMeRepository : MeRepository

    @get:[Binds]
    val FeaturedRepositoryImp.provideFeaturedRepository : FeaturedRepository

    @get:[Binds]
    val AuthRepositoryImp.provideAuthRepository : AuthRepository

}