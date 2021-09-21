package app.hdj.datepick.android.ui.screens.others.course

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.LargeTitleAndSubtitle
import app.hdj.datepick.ui.components.TitledLazyListScaffold
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun CourseScreen(
    courseId: String? = null,
    vm: CourseViewModelDelegate = hiltViewModel<CourseViewModel>()
) {

    val (state, effect, event) = vm.extract()

    TitledLazyListScaffold(
        title = { Text(text = "홈") },
        expandedTitle = {
            LargeTitleAndSubtitle(title = "코스 제목", subtitle = "내용이 들어갈 곳입니다.")
        },
        navIcons = { TopAppBarBackButton() }
    ) {


    }


}

@Composable
@Preview
fun CourseScreenPreview() {
    DatePickTheme {
        CourseScreen(vm = fakeCourseViewModel())
    }
}