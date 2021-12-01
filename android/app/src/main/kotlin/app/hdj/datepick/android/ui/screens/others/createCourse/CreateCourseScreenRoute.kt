package app.hdj.datepick.android.ui.screens.others.createCourse

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.createCourseScreen() {

    composable(AppNavigationGraph.CreateCourse.route) {
        CreateCourseScreen()
    }

}
