package app.hdj.datepick.android.ui.providers.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.model.user.User
import kotlin.random.Random

class FakeUserPreviewProvider : PreviewParameterProvider<User?> {
    override val values: Sequence<User?>
        get() = sequenceOf(
            null,
            object : User {
                override val id: Long = Random.nextLong()
                override val nickname: String = "Harry"
                override val isMe: Boolean = true
                override val profileImageUrl: String = ""
            }
        )
}