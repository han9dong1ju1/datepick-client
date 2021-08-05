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
    fun provideDbDriver(@ApplicationContext context: Context) =
        AndroidSqliteDriver(DatepickDatabase.Schema, context, name = "datepick-database.db")

    @Provides
    @Singleton
    fun provideDb(dbDriver: SqlDriver) = DatepickDatabase(dbDriver)

    @Provides
    @Singleton
    fun providePlaceEntityQuery(db : DatepickDatabase) : PlaceEntityQueries = db.placeEntityQueries

    @Provides
    @Singleton
    fun provideCourseEntityQuery(db : DatepickDatabase) : CourseEntityQueries = db.courseEntityQueries

}