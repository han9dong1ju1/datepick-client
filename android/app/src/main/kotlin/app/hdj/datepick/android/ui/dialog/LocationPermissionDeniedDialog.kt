package app.hdj.datepick.android.ui.dialog

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.hdj.datepick.ui.components.CallToActionButton
import app.hdj.datepick.ui.components.UnAccentButton
import app.hdj.datepick.ui.components.dialog.DialogContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

@Composable
@Destination(style = DestinationStyle.Dialog::class)
fun LocationPermissionDeniedDialog(
    navigator: DestinationsNavigator
) {
    LocationPermissionDeniedDialogContent {
        navigator.popBackStack()
    }
}

@Composable
private fun LocationPermissionDeniedDialogContent(
    popBackStack : () -> Unit
) {

    val context = LocalContext.current

    Dialog(
        onDismissRequest = { popBackStack() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        content = {
            DialogContent(
                "위치 권한이 거부되어있습니다.",
                "위치 권한이 거부되어, 위치 서비스를 사용할 수 없습니다.\n설정에서 위치권한을 허용해주세요.",
                { Icon(imageVector = Icons.Rounded.Place, null) },
                positiveButton = {
                    CallToActionButton(modifier = Modifier.fillMaxWidth(), text = "설정으로 이동") {
                        context.startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            }
                        )
                        popBackStack()
                    }
                },
                negativeButton = {
                    UnAccentButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = "취소"
                    ) { popBackStack() }
                }
            )
        }
    )
}