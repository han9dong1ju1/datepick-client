package app.hdj.datepick.android.service

import app.hdj.datepick.data.datastore.AppDataStore
import app.hdj.datepick.domain.repo.UserRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class PushNotificationService : FirebaseMessagingService(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    @Inject
    lateinit var dataStore: app.hdj.datepick.data.datastore.AppDataStore

    @Inject
    lateinit var userRepository: app.hdj.datepick.domain.repo.UserRepository

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        dataStore.notificationSettings.onEach { notificationSettings ->

            if (!notificationSettings.all) return@onEach


        }.launchIn(this)

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        userRepository.updateFirebaseToken(token).onEach {

        }.launchIn(this)
    }

}