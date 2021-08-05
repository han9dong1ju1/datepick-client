package app.hdj.datepick.data.datastore

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.serialization.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

interface MeDataStore : DataStore<User> {
    val me: User?
    fun delete()
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

    override val me: User? by settings.nullableSerializedValue(MeEntity.serializer(), KEY_ME)

    override fun save(data: User) {
        settings.encodeValue(MeEntity.serializer(), KEY_ME, MeEntity.fromUser(data))
    }

    override fun delete() {
        settings.remove(KEY_ME)
    }

    companion object {
        const val KEY_ME = "me"
    }

}