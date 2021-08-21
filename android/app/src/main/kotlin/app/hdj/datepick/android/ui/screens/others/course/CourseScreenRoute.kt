package app.hdj.datepick.android.ui.screens.others.course

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.appNavigationComposable

fun NavGraphBuilder.courseScreen() {
    appNavigationComposable(AppNavigationGraph.Course) {
        val courseId =
            it.arguments?.getString(AppNavigationGraph.Course.ARGUMENT_COURSE_ID)
        CourseScreen(courseId)
    }
}
