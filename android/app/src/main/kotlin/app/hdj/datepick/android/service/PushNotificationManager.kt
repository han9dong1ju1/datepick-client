package app.hdj.datepick.android.service

import android.content.Context
import android.graphics.Bitmap
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.toBitmap
import app.hdj.datepick.domain.model.pushNotification.PushNotificationData
import coil.imageLoader
import coil.request.ImageRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

interface PushNotificationManager {

    fun showNotification(pushNotificationData: PushNotificationData)

}

@Singleton
class PushNotificationManagerImp @Inject constructor(
    @ApplicationContext private val context: Context
) : PushNotificationManager, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private fun createNotificationChannels() {

    }

    init {
        createNotificationChannels()
    }

    override fun showNotification(pushNotificationData: PushNotificationData) {

        NotificationManagerCompat.from(context).notify(0, context.buildNotification())
    }

    private suspend fun String.loadIconBitmap(
        requestBuilder: ImageRequest.Builder.() -> ImageRequest.Builder
    ): Bitmap? {

        val request = ImageRequest.Builder(context = context)
            .data(this)
            .dispatcher(Dispatchers.IO)
            .requestBuilder()
            .build()

        return context.imageLoader.execute(request).drawable?.toBitmap()
    }

    private fun Context.buildNotification(

    ) = NotificationCompat.Builder(this, "")
            .setContentTitle("")
            .build()

}