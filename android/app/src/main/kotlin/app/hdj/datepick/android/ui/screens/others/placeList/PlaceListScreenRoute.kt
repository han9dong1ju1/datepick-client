package app.hdj.datepick.android.ui.screens.others.placeList

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceList
import app.hdj.datepick.android.ui.screens.appNavigationComposable

fun NavGraphBuilder.placeListScreen() {

    appNavigationComposable(PlaceList) {
        val navController = LocalAppNavController.current

        PlaceListScreen(fakePlaceListViewModel())
    }

}
