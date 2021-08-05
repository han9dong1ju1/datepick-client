package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.compositionLocalOf
import app.hdj.datepick.domain.model.user.User

val LocalMe = compositionLocalOf<User?> { null }