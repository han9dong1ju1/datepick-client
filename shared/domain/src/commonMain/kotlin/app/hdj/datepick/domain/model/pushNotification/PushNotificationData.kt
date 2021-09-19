package app.hdj.datepick.domain.model.pushNotification

import kotlinx.serialization.Serializable

@Serializable
data class PushNotificationData(
    val id : String,
    val icon : String? = null,
    val image : String? = null,
    val type : PushNotificationType,
    val title : String,
    val content : String,
    val link : String,
    val createdAt : String
) {

    enum class PushNotificationType {
        NOTICE,
        EVENT,
        COURSE_RECOMMENDATION,
        PICK,
    }

}