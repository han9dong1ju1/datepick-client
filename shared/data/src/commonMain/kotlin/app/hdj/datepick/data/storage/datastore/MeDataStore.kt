package app.hdj.datepick.data.storage.datastore

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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

@OptIn(ExperimentalSerializationApi::class)
@Singleton
class MeDataStoreImp @Inject constructor(
    @Named("encrypted") private val settings: Settings
) : MeDataStore {

    private val json = Json {
        ignoreUnknownKeys = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = true
        prettyPrint = true
        explicitNulls = false
        allowStructuredMapKeys = true
    }

    private val meFlow = MutableStateFlow<User?>(null)

    override val observableMe get() = meFlow

    override suspend fun cachedMe(): User? {
        return settings.getStringOrNull(KEY_ME)?.let {
            json.decodeFromString(MeEntity.serializer(), it)
        }?.toUser()
    }

    override suspend fun save(data: User) {
        settings.putString(KEY_ME, json.encodeToString(MeEntity.serializer(), MeEntity.fromUser(data)))
        meFlow.emit(data)
    }

    override suspend fun clearMe() {
        settings.remove(KEY_ME)
        meFlow.emit(null)
    }

    companion object {
        const val KEY_ME = "me"
    }

}