package app.hdj.datepick.android.ui.screens.others.course

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun CourseScreen(
    courseId: String? = null,
    vm: CourseViewModelDelegate = hiltViewModel<CourseViewModel>()
) {

    val (state, effect, event) = vm.extract()



}

@Composable
@Preview
fun CourseScreenPreview() {
    BaseTheme {
        CourseScreen(vm = fakeCourseViewModel())
    }
}