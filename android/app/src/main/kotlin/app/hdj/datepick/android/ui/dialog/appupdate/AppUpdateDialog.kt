package app.hdj.datepick.android.ui.dialog.appupdate

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.ui.utils.extract


fun NavGraphBuilder.appUpdateDialog() {

    dialog(AppNavigationGraph.AppUpdateDialog.route) {

        val vm: AppUpdateViewModelDelegate = hiltViewModel<AppUpdateViewModel>()

        val (state, effect, event) = vm.extract()

        val navController = LocalAppNavController.current

        val result = state.appUpdateResult

    }

}