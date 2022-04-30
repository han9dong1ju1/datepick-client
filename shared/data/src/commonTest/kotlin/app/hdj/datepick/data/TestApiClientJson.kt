package app.hdj.datepick.data

import app.hdj.datepick.data.model.response.user.UserResponse
import app.hdj.datepick.data.remote.ApiResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue

private val json = Json {
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = true
    prettyPrint = true
}

class TestApiClientJson {

    @Test
    fun testKotlinSerialization() {
        val parsed = json.decodeFromString<ApiResponse<UserResponse>>(
            """
                {
                  "message": null,
                  "error": null,
                  "data": {
                    "id": 10007,
                    "created_at": "2022-04-30T16:23:48.231916",
                    "updated_at": "2022-04-30T16:23:48.231916",
                    "uid": "103897723461882838477",
                    "provider": "GOOGLE",
                    "nickname": "TYLENOL21474836",
                    "email": "jungsanmango@gmail.com",
                    "gender": null,
                    "is_banned": false,
                    "is_deleted": false,
                    "image_url": null
                  }
                }
            """.trimIndent()
        )

        println(parsed)

        assertTrue(parsed.data.nickname == "TYLENOL21474836")
    }

}