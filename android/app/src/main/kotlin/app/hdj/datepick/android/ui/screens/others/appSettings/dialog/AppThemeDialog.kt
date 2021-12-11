package app.hdj.datepick.android.ui.screens.others.appSettings.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import app.hdj.datepick.android.ui.screens.others.appSettings.AppSettingsViewModelDelegate
import app.hdj.datepick.domain.model.settings.AppTheme
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.components.TextRadioButton
import app.hdj.datepick.ui.utils.extract

@Composable
fun AppThemeDialog(
    vm: AppSettingsViewModelDelegate,
    settingsNavController: NavController
) {
    val (state, effect, event) = vm.extract()

    Dialog(onDismissRequest = {
        settingsNavController.popBackStack()
    }) {
        Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
            Column(modifier = Modifier.padding(10.dp)) {
                Header(title = "앱 테마 설정")
                TextRadioButton("밝은 테마", state.appTheme == AppTheme.Light) {
                    event(AppSettingsViewModelDelegate.Event.UpdateTheme(AppTheme.Light))
                }
                TextRadioButton("어두운 테마", state.appTheme == AppTheme.Dark) {
                    event(AppSettingsViewModelDelegate.Event.UpdateTheme(AppTheme.Dark))
                }
                TextRadioButton("시스템 설정에 따른 테마", state.appTheme == AppTheme.System) {
                    event(AppSettingsViewModelDelegate.Event.UpdateTheme(AppTheme.System))
                }
            }
        }
    }

}