package app.hdj.datepick.android.ui.screens.others.place_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceList
import app.hdj.datepick.android.ui.screens.appNavigationComposable

fun NavGraphBuilder.placeListScreen() {

    appNavigationComposable(PlaceList) {
        val navController = LocalAppNavController.current

        PlaceListScreen()
    }

}
