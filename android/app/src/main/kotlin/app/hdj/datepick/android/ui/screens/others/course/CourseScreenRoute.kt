package app.hdj.datepick.android.ui.screens.others.course

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.appNavigationComposable

fun NavGraphBuilder.courseScreen() {
    appNavigationComposable(AppNavigationGraph.Course) {
        val courseId =
            it.arguments?.getLong(AppNavigationGraph.Course.ARGUMENT_COURSE_ID)
        CourseScreen(courseId)
    }
}
