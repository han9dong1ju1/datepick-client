package app.hdj.datepick.android.di

import app.hdj.datepick.android.service.PushNotificationManager
import app.hdj.datepick.android.service.PushNotificationManagerImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PushNotificationModule {

    @get:[Binds]
    val PushNotificationManagerImp.pushNotificationManager : PushNotificationManager

}