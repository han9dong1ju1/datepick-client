package app.hdj.datepick.android.ui.providers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class DeviceType {
    SmallPhone,
    LargePhone,
    Tablet
}

val LocalDeviceType = compositionLocalOf { DeviceType.SmallPhone }

@Composable
fun ProvideDeviceType(content: @Composable () -> Unit) {

    val configuration = LocalConfiguration.current
    val deviceType = when (configuration.screenWidthDp) {
        in 0..360 -> DeviceType.SmallPhone
        in 360 until 600 -> DeviceType.LargePhone
        else -> DeviceType.Tablet
    }

    CompositionLocalProvider(LocalDeviceType provides deviceType, content = content)
}

@Composable
private fun DeviceTypePreview() {
    MaterialTheme {
        ProvideDeviceType {
            val deviceType = LocalDeviceType.current

            val configuration = LocalConfiguration.current
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "I'm ${deviceType.name}",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = "Screen Width : ${configuration.screenWidthDp}",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_5)
fun PreviewSmallPhone() {
    DeviceTypePreview()
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL)
fun PreviewLargePhone() {
    DeviceTypePreview()
}

@Composable
@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_7)
fun PreviewTablet() {
    DeviceTypePreview()
}