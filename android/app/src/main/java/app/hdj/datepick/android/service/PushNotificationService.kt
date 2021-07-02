package app.hdj.datepick.android.service

import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.domain.repo.UserRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class PushNotificationService : FirebaseMessagingService(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    @Inject
    lateinit var dataStore: AppDataStore

    @Inject
    lateinit var userRepository: UserRepository

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