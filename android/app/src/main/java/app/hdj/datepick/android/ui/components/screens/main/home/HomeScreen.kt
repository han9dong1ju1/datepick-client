package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.LargeTitle
import app.hdj.datepick.ui.components.SearchBar
import app.hdj.datepick.ui.components.TitledLazyListScaffold
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.CourseQuery
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.entity.Place

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>(),
    onShowMoreCourses: (CourseQuery) -> Unit = {},
    onShowMorePlaces: (PlaceQuery) -> Unit = {},
    onPlaceClicked: (Place) -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
    onSearchPlaceClicked: () -> Unit = {},
) {

    val (state, effect, event) = vm.extract()

    TitledLazyListScaffold(
        title = { Text(text = "홈") },
        expandedTitle = { LargeTitle(text = "홈") },
        topAppBarActions = {
            IconButton(onClick = {
                onSearchPlaceClicked()
            }) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
            }
        }
    ) {
        stickyHeader {

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