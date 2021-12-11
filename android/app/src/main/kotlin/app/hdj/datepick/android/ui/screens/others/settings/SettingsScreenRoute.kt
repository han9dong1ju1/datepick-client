package app.hdj.datepick.android.ui.screens.others.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import com.google.accompanist.navigation.animation.navigation
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.AppSettings.List
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.android.ui.screens.others.settings.setting_list.AppSettingsListScreen
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.components.TextRadioButtonGroup
import app.hdj.datepick.ui.components.rememberTextRadioButtonGroupState

fun NavGraphBuilder.appSettingsScreens() {


    navigation(
        startDestination = List.route,
        route = AppNavigationGraph.AppSettings.route
    ) {

        appNavigationComposable(List) {
            AppSettingsListScreen()
        }

        dialog(AppNavigationGraph.AppSettings.AppThemeDialog.route) {
            val navController = LocalAppNavController.current
            val radioButtonGroupState = rememberTextRadioButtonGroupState()

            Dialog(onDismissRequest = {
                navController.popBackStack()
            }) {
                Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
                    Column {
                        Header(title = "앱 테마 설정")
                        TextRadioButtonGroup(
                            texts = listOf("어두운 테마", "밝은 테마", "시스템 설정에 따름"),
                            state = radioButtonGroupState
                        ) {

                        }
                    }
                }
            }
        }

    }

}
