package app.hdj.datepick.android.ui.screens.others.createCourse

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.appNavigationComposable

fun NavGraphBuilder.createCourseScreen() {

    appNavigationComposable(AppNavigationGraph.CreateCourse) {
        CreateCourseScreen(
            it.arguments?.getString(AppNavigationGraph.CreateCourse.ARGUMENT_COURSE_ID)
        )
    }

}
