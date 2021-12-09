package app.hdj.datepick.android.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.graphics.drawable.toBitmap
import app.hdj.datepick.MR
import app.hdj.datepick.data.utils.res
import app.hdj.datepick.domain.model.pushNotification.PushNotificationData
import app.hdj.datepick.domain.model.pushNotification.PushNotificationData.PushNotificationType
import app.hdj.datepick.domain.model.pushNotification.PushNotificationData.PushNotificationType.*
import coil.imageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

internal data class PushNotificationChannelData(
    val type: PushNotificationType,
    val name: String
)

interface PushNotificationManager {

    fun updateToken(token : String)
    fun showNotification(pushNotificationData: PushNotificationData)

}

@Singleton
class PushNotificationManagerImp @Inject constructor(
    @ApplicationContext private val context: Context
) : PushNotificationManager, CoroutineScope {

    override val coroutineContext: CoroutineContext get() = Dispatchers.Main

    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        val notificationChannelData = PushNotificationType.values().map { type ->
            val nameRes = when (type) {
                NOTICE -> MR.strings.title_notice
                EVENT -> MR.strings.title_event
                COURSE_RECOMMENDATION -> MR.strings.title_course_recommendation
                PICK -> MR.strings.title_pick
            }
            PushNotificationChannelData(type, context.res(nameRes))
        }

        val channels = notificationChannelData.map { (type, name) ->
            NotificationChannelCompat.Builder(type.name, NotificationManager.IMPORTANCE_HIGH)
                .setName(name)
                .build()
        }

        notificationManager.createNotificationChannelsCompat(channels)
    }

    override fun showNotification(pushNotificationData: PushNotificationData) {
        launch {
            val notification = buildDefaultNotification(pushNotificationData)
            notificationManager.notify(0, notification)
        }
    }

    private suspend fun String?.loadIconBitmap(requestBuilder: ImageRequest.Builder.() -> ImageRequest.Builder = { this }): Bitmap? {
        if (this == null) return null

        val request = ImageRequest.Builder(context = context)
            .data(this)
            .dispatcher(Dispatchers.IO)
            .requestBuilder()
            .build()

        return context.imageLoader.execute(request).drawable?.toBitmap()
    }

    private suspend fun buildDefaultNotification(
        pushNotificationData: PushNotificationData
    ): Notification = with(pushNotificationData) {

        val largeIcon = largeIcon.loadIconBitmap {
            transformations(CircleCropTransformation())
        }

        val image = image.loadIconBitmap {
            transformations(RoundedCornersTransformation(20f))
        }

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(link)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        NotificationCompat.Builder(context, type.name)
            .setContentTitle(title)
            .setContentText(content)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(app.hdj.datepick.android.R.drawable.ic_launcher_foreground)
            .setContentIntent(
                TaskStackBuilder.create(context)
                    .addNextIntent(intent)
                    .getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT)
            )
            .apply {
                if (largeIcon != null) setLargeIcon(largeIcon)
                if (image != null) {
                    setStyle(
                        NotificationCompat.BigPictureStyle()
                            .bigLargeIcon(largeIcon)
                            .bigPicture(image)
                    )
                }
            }
            .build()
    }

    override fun updateToken(token: String) {
        launch {

        }
    }
}