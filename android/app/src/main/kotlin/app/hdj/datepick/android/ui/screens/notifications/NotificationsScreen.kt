package app.hdj.datepick.android.ui.screens.notifications

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun NotificationScreen(
    navigator: DestinationsNavigator
) {
    NotificationsScreenContent()
}

@Composable
private fun NotificationsScreenContent() {

    BaseScaffold(
        topBar = {
            InsetTopBar(
                title = { Text("알림") },
                navigationIcon = { TopAppBarBackButton() }
            )
        }
    ) {

    }

}