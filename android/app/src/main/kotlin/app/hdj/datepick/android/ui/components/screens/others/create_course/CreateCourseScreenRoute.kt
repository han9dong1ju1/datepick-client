package app.hdj.datepick.android.ui.components.screens.others.create_course

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph

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
