package app.hdj.datepick.android.ui.screens.others.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import com.google.accompanist.navigation.animation.navigation
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.AppSettings.List
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.android.ui.screens.others.settings.setting_list.AppSettingsListScreen
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.ui.components.TextRadioButtonGroup
import app.hdj.datepick.ui.components.rememberTextRadioButtonGroupState
import app.hdj.datepick.ui.utils.extract

fun NavGraphBuilder.appSettingsScreens() {

    navigation(
        startDestination = List.route,
        route = AppNavigationGraph.AppSettings.route
    ) {

        appNavigationComposable(List) {
            AppSettingsListScreen()
        }

        dialog(AppNavigationGraph.AppSettings.AppThemeDialog.route) {
            val (state, _, event) = LocalDatePickAppViewModel.current.extract()

            val themes = remember {
                listOf(
                    AppSettings.AppTheme.Light,
                    AppSettings.AppTheme.Dark,
                    AppSettings.AppTheme.System,
                )
            }

            val navController = LocalAppNavController.current
            val radioButtonGroupState =
                rememberTextRadioButtonGroupState(themes.indexOf(state.appTheme))

            Dialog(onDismissRequest = {
                navController.popBackStack()
            }) {
                Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
                    Column {
                        Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 30.dp)) {
                            Text(
                                text = "앱 테마 설정",
                                style = MaterialTheme.typography.h6
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "적용할 앱 테마를 선택해주세요.",
                                style = MaterialTheme.typography.body2
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        TextRadioButtonGroup(
                            texts = listOf("밝은 테마", "어두운 테마", "시스템 설정에 따름"),
                            state = radioButtonGroupState
                        ) {
                            event(
                                DatePickAppViewModelDelegate.Event.UpdateAppTheme(
                                    themes[radioButtonGroupState.selectedIndex]
                                )
                            )
                        }
                    }
                }
            }
        }

    }

}
