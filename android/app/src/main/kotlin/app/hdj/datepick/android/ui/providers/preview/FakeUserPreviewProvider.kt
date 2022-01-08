package app.hdj.datepick.android.ui.providers.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import kotlin.random.Random

class FakeUserPreviewProvider : PreviewParameterProvider<User?> {
    override val values: Sequence<User?>
        get() = sequenceOf(
            object : User {
                override val id: Long = Random.nextLong()
                override val nickname: String = "Harry"
                override val gender: UserGender? = null
                override val isMe: Boolean = true
                override val imageUrl: String = "https://picsum.photos/200"
            },
            null
        )
}