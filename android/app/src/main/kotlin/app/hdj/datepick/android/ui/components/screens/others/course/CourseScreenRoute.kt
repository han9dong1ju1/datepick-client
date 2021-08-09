package app.hdj.datepick.android.ui.components.screens.others.course

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph

fun NavGraphBuilder.courseScreen() {
    composable(
        AppNavigationGraph.Course.route, listOf(
            navArgument(AppNavigationGraph.Course.ARGUMENT_COURSE_ID) {
                type = NavType.StringType
            }
        )) {
        val courseId =
            it.arguments?.getString(AppNavigationGraph.Course.ARGUMENT_COURSE_ID)
        CourseScreen(courseId)
    }
}
