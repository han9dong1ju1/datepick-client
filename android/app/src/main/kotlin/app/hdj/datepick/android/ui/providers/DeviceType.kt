package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration

enum class DeviceType {
    Phone360,
    Tablet640
}

val LocalDeviceType = compositionLocalOf { DeviceType.Phone360 }

@Composable
fun ProvideDeviceType(content: @Composable () -> Unit) {

    val configuration = LocalConfiguration.current
    val deviceType = when {
        configuration.screenWidthDp <= 360 -> DeviceType.Phone360
        else -> DeviceType.Tablet640
    }

    CompositionLocalProvider(LocalDeviceType provides deviceType, content = content)

}