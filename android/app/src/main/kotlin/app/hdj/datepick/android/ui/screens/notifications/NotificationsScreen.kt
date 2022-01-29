package app.hdj.datepick.android.ui.screens.notifications

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton

@Composable
fun NotificationsScreen() {

    BaseScaffold(
        topBar = {
            BaseTopBar(
                title = { Text("알림") },
                navigationIcon = { TopAppBarBackButton() }
            )
        }
    ) {

    }

}