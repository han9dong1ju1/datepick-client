package app.hdj.datepick.data.datastore

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

interface MeDataStore : DataStore<User> {
    val observableMe: Flow<User?>
    suspend fun cachedMe(): User?
    suspend fun clearMe()
}

@Serializable
data class MeEntity(
    val id: Long,
    val nickname: String,
    val imageUrl: String?,
    val gender: UserGender?
) {

    fun toUser() = User(id, nickname, imageUrl, gender)

    companion object {
        fun fromUser(user: User) = with(user) {
            MeEntity(id, nickname, imageUrl, gender)
        }
    }

}

@OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
@Singleton
class MeDataStoreImp @Inject constructor(
    private val settings: FlowSettings
) : MeDataStore {

    private val json = Json {
        ignoreUnknownKeys = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = true
        prettyPrint = true
        explicitNulls = false
        allowStructuredMapKeys = true
    }

    override val observableMe = settings.getStringOrNullFlow(KEY_ME).map { value ->
        value?.let { json.decodeFromString(MeEntity.serializer(), it) }?.toUser()
    }

    override suspend fun cachedMe(): User? {
        return settings.getStringOrNull(KEY_ME)?.let {
            json.decodeFromString(MeEntity.serializer(), it)
        }?.toUser()
    }

    override suspend fun save(data: User) {
        settings.putString(KEY_ME, json.encodeToString(MeEntity.serializer(), MeEntity.fromUser(data)))
    }

    override suspend fun clearMe() {
        settings.remove(KEY_ME)
    }

    companion object {
        const val KEY_ME = "me"
    }

}