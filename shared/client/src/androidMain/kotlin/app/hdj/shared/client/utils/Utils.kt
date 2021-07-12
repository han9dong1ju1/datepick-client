package app.hdj.shared.client.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.hdj.client.BuildConfig
import dev.icerock.moko.resources.StringResource

actual val isDebug: Boolean = BuildConfig.DEBUG

@Composable
fun StringResource.string() = stringResource(this.resourceId)