package app.hdj.datepick.ui.screens.course

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun CourseScreen(loginViewModel: CourseViewModelDelegate = hiltViewModel<CourseViewModel>()) {

    val (state, effect, event) = loginViewModel.extract()


}

@Composable
@Preview
fun CourseScreenPreview() {
    DatePickTheme {
        CourseScreen(fakeCourseViewModel())
    }
}