package app.hdj.datepick.data.di

import android.content.Context
import app.hdj.datepick.*
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDbDriver(@ApplicationContext context: Context) : SqlDriver =
        AndroidSqliteDriver(DatePickDatabase.Schema, context, name = "datepick-database.db")

    @Provides
    @Singleton
    fun provideDb(dbDriver: SqlDriver) = DatePickDatabase(dbDriver)

    @Provides
    @Singleton
    fun providePlaceEntityQuery(db : DatePickDatabase) : PlaceEntityQueries = db.placeEntityQueries

    @Provides
    @Singleton
    fun provideCourseEntityQuery(db : DatePickDatabase) : CourseEntityQueries = db.courseEntityQueries

    @Provides
    @Singleton
    fun provideFeaturedEntityQuery(db : DatePickDatabase) : FeaturedEntityQueries = db.featuredEntityQueries

}