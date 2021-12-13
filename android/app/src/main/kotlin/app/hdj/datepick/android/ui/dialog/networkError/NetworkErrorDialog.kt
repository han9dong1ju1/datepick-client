package app.hdj.datepick.android.ui.dialog.networkError

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.UnAccentButton
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.getActivity

fun NavGraphBuilder.networkErrorDialog() {
    dialog(AppNavigationGraph.NetworkErrorDialog.route) {
        val (_, _, event) = LocalDatePickAppViewModel.current.extract()
        val navController = LocalAppNavController.current
        val activity = LocalContext.current.getActivity()
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
                Column {
                    Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 30.dp)) {
                        Text(
                            text = "네트워크에 문제가 발생했습니다.",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "재시도 버튼을 눌러 다시 시도해보세요.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        UnAccentButton(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 5.dp),
                            shape = RoundedCornerShape(10.dp),
                            text = "종료"
                        ) {
                            activity?.finishAndRemoveTask()
                        }
                        BaseButton(
                            shape = RoundedCornerShape(10.dp),
                            text = "재시도",
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp)
                        ) {
                            navController.popBackStack()
                            event(DatePickAppViewModelDelegate.Event.RetryFetchMe)
                        }
                    }
                }
            }
        }
    }
}