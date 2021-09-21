package app.hdj.datepick.android.ui.screens.others.userProfileEdit

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.appNavigationComposable

fun NavGraphBuilder.userProfileEditScreenRoute() {

    appNavigationComposable(AppNavigationGraph.UserProfileEdit) {
        UserProfileEditScreen()
    }

}