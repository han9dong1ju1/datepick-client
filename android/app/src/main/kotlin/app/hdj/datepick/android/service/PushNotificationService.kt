package app.hdj.datepick.android.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class PushNotificationService : FirebaseMessagingService(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)


    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}