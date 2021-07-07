package app.hdj.datepick.android.ui.components.screens.others.create_course

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun CreateCourseScreen(
    courseId: String? = null,
    vm: CreateCourseViewModelDelegate = hiltViewModel<CreateCourseViewModel>()
) {

    val (state, effect, event) = vm.extract()



}

@Composable
@Preview
fun CreateCourseScreenPreview() {
    DatePickTheme {
        CreateCourseScreen(vm = fakeCreateCourseViewModel())
    }
}