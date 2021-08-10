package app.hdj.datepick.data.di

import app.hdj.datepick.data.repository.CourseRepositoryImp
import app.hdj.datepick.data.repository.FeaturedRepositoryImp
import app.hdj.datepick.data.repository.PlaceRepositoryImp
import app.hdj.datepick.data.repository.MeRepositoryImp
import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.domain.repository.PlaceRepository
import app.hdj.datepick.domain.repository.MeRepository
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

}