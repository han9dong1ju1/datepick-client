package app.hdj.datepick.android.ui.screens.others.createCourse

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.createCourseScreen() {

    appNavigationComposable(AppNavigationGraph.CreateCourse) {
        CreateCourseScreen()
    }

}
