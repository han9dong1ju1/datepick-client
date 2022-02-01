package app.hdj.datepick.android.ui.screens.main.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopBar


@Composable
fun ProfileScreen() {

    val navController = LocalAppNavController.current

    BaseScaffold(
        topBar = {
            BaseTopBar(
                title = {
                    Text("프로필")
                },
                actions = {
                    IconButton({
                        navController.navigateRoute(AppNavigationGraph.AppSettings)
                    }) {
                        Icon(imageVector = Icons.Rounded.Settings, null)
                    }
                }
            )
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it)) {

        }

    }

}