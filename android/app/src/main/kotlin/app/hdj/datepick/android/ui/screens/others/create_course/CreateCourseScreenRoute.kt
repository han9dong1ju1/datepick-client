package app.hdj.datepick.android.ui.screens.others.create_course

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.screens.AppNavigationGraph

fun NavGraphBuilder.createCourseScreen() {

    composable(
        AppNavigationGraph.CreateCourse.route, listOf(
            navArgument(AppNavigationGraph.CreateCourse.ARGUMENT_COURSE_ID) {
                type = NavType.StringType
            }
        )) {
        CreateCourseScreen(
            it.arguments?.getString(AppNavigationGraph.CreateCourse.ARGUMENT_COURSE_ID)
        )
    }

}
