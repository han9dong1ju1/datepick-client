package app.hdj.datepick.data.datastore

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.toSuspendSettings
import com.russhwolf.settings.serialization.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

interface MeDataStore : DataStore<User> {
    val observableMe: Flow<User?>
    val me: User?
    suspend fun clearMe()
}

@Serializable
data class MeEntity(
    override val id: String,
    override val nickname: String,
    override val isMe: Boolean,
    override val profileImageUrl: String?
) : User {
    companion object {
        fun fromUser(user: User) = with(user) {
            MeEntity(id, nickname, true, profileImageUrl)
        }
    }
}

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
@Singleton
class MeDataStoreImp @Inject constructor(
    private val settings: Settings
) : MeDataStore {

    override val observableMe = MutableStateFlow<User?>(null)

    override val me: User? by settings.nullableSerializedValue(MeEntity.serializer(), KEY_ME)

    override suspend fun save(data: User) {
        settings.encodeValue(MeEntity.serializer(), KEY_ME, MeEntity.fromUser(data))
        observableMe.emit(data)
    }

    override suspend fun clearMe() {
        settings.remove(KEY_ME)
        observableMe.emit(null)
    }

    companion object {
        const val KEY_ME = "me"
    }

}