package app.hdj.datepick.android.ui.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.presentation.settings.SettingsScreenViewModel
import app.hdj.datepick.presentation.settings.SettingsScreenViewModelDelegate
import app.hdj.datepick.ui.components.CallToActionButton
import app.hdj.datepick.ui.components.UnAccentButton
import app.hdj.datepick.ui.components.dialog.DialogContent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

@Composable
@Destination(style = DestinationStyle.Dialog::class)
fun AppThemeDialog(
    navigator: DestinationsNavigator
) {
    AppThemeDialogContent(hiltViewModel<SettingsScreenViewModel>()) {
        navigator.popBackStack()
    }
}

@Composable
private fun AppThemeDialogContent(
    vm: SettingsScreenViewModelDelegate,
    popBackStack : () -> Unit,
) {

    val (state, effect, event) = vm.extract()

    val themes = listOf(
        AppSettings.AppTheme.Light to "밝음",
        AppSettings.AppTheme.Dark to "어두움",
        AppSettings.AppTheme.System to "시스템 설정에 따름"
    )

    var selectedTheme by remember(state.appTheme) {
        mutableStateOf(state.appTheme)
    }

    Dialog(
        onDismissRequest = { popBackStack() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        content = {
            DialogContent(
                "앱 테마 설정.",
                "적용할 앱 테마를 선택해주세요.",
                topContent = { Icon(imageVector = Icons.Rounded.Palette, null) },
                bottomContent = {
                    themes.forEach {
                        Surface(
                            onClick = {
                                selectedTheme = it.first
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Row(modifier = Modifier.padding(20.dp)) {
                                RadioButton(
                                    selected = selectedTheme == it.first,
                                    onClick = null
                                )
                                Spacer(Modifier.width(20.dp))
                                Text(it.second)
                            }
                        }
                    }
                },
                positiveButton = {
                    CallToActionButton(modifier = Modifier.fillMaxWidth().height(50.dp), text = "확인") {
                        event(SettingsScreenViewModelDelegate.Event.SwitchTheme(selectedTheme))
                        popBackStack()
                    }
                },
                negativeButton = {
                    UnAccentButton(
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        text = "취소"
                    ) { popBackStack() }
                }
            )
        }
    )
}