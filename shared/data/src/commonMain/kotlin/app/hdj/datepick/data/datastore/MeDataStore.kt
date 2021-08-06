package app.hdj.datepick.data.datastore

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import com.russhwolf.settings.serialization.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface MeDataStore : DataStore<User> {
    val observableMe: Flow<User?>
    suspend fun cachedMe(): User?
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
    private val settings: FlowSettings
) : MeDataStore {

    override val observableMe = settings.getStringOrNullFlow(KEY_ME).map { value ->
        value?.let { Json.decodeFromString(MeEntity.serializer(), it) }
    }

    override suspend fun cachedMe(): User? {
        return settings.getStringOrNull(KEY_ME)?.let {
            Json.decodeFromString(it)
        }
    }

    override suspend fun save(data: User) {
        settings.putString(KEY_ME, Json.encodeToString(MeEntity.fromUser(data)))
    }

    override suspend fun clearMe() {
        settings.remove(KEY_ME)
    }

    companion object {
        const val KEY_ME = "me"
    }

}