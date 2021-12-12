package app.hdj.datepick.android.ui.dialog.exit

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.components.UnAccentButton
import kotlin.system.exitProcess

fun NavGraphBuilder.exitDialog() {

    dialog(AppNavigationGraph.ExitDialog.route) {
        val navController = LocalAppNavController.current

        Dialog(onDismissRequest = {
            navController.popBackStack()
        }) {
            Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {
                Column {
                    Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 30.dp)) {
                        Text(
                            text = "앱을 종료하시겠습니까?",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "종료버튼을 누르면 앱이 종료됩니다.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)) {
                        UnAccentButton(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 5.dp),
                            shape = RoundedCornerShape(10.dp),
                            text = "취소"
                        ) {
                            navController.popBackStack()
                        }
                        BaseButton(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp),
                            shape = RoundedCornerShape(10.dp),
                            text = "종료"
                        ) {
                            exitProcess(0)
                        }
                    }
                }
            }
        }
    }

}