package app.hdj.datepick.android.service

import app.hdj.datepick.domain.model.pushNotification.PushNotificationData
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
@OptIn(ExperimentalSerializationApi::class)
class PushNotificationService : FirebaseMessagingService(), CoroutineScope {

    @Inject
    lateinit var pushNotificationManager: PushNotificationManager

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val params = message.data["params"] ?: return
        val data = Json.decodeFromString<PushNotificationData>(params)
        pushNotificationManager.showNotification(data)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        pushNotificationManager.updateToken(token)
    }

}