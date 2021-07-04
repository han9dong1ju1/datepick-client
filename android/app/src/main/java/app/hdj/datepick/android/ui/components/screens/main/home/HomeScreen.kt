package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.LargeTitle
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.CourseQuery
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.entity.Place

@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>(),
    onShowMoreCourses: (CourseQuery) -> Unit = {},
    onShowMorePlaces: (PlaceQuery) -> Unit = {},
    onPlaceClicked: (Place) -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
) {

    val (state, effect, event) = vm.extract()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            LargeTitle(text = "홈")
        }
    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}