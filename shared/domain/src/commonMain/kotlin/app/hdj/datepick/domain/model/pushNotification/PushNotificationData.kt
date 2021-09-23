package app.hdj.datepick.domain.model.pushNotification

import kotlinx.serialization.Serializable

@Serializable
data class PushNotificationData(
    val id : Long,
    val largeIcon : String? = null,
    val image : String? = null,
    val type : PushNotificationType,
    val title : String,
    val content : String,
    val link : String
) {

    enum class PushNotificationType {
        NOTICE,
        EVENT,
        COURSE_RECOMMENDATION,
        PICK,
    }

}