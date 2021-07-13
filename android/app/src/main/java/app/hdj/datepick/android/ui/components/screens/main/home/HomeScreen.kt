package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.course.courseRowList
import app.hdj.datepick.android.ui.components.list.moreButtonHeader
import app.hdj.datepick.android.ui.components.list.place.RankedPlaceHorizontalPager
import app.hdj.datepick.ui.components.DatePickScrollableTabRow
import app.hdj.datepick.ui.components.TitledLazyListScaffold
import app.hdj.datepick.ui.components.tabIndicatorOffset
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.verticalMargin
import app.hdj.shared.client.domain.CourseQuery
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.entity.Place
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>(),
    onShowMoreCourses: (CourseQuery) -> Unit = {},
    onShowMorePlaces: (PlaceQuery) -> Unit = {},
    onPlaceClicked: (Place) -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
) {

    val (state, effect, event) = vm.extract()

    var selectedCategoryIndex by remember { mutableStateOf(0) }

    val selectedRecommendedPlaces by state.recommendedPlacesFlow.collectAsState(StateData.Loading())

    val location = "서울시 종로구"

    val onLocationChangeClicked = {

    }

    TitledLazyListScaffold(
        enableDivider = false,
        title = { HomeCollapsedTitle(location, onLocationChangeClicked) },
        expandedTitle = { HomeExpandedTitle(location, onLocationChangeClicked) }
    ) {

        stickyHeader { HomeTags(state.tags) }
        verticalMargin()

        moreButtonHeader("인기있는 코스들", "더보기") {}
        courseRowList(stateData = state.popularCourses, onCourseClicked)
        verticalMargin(40.dp)

        moreButtonHeader("인기있는 코스들", "더보기") {}
        item {
            RankedPlaceHorizontalPager(
                modifier = Modifier.fillParentMaxWidth(),
                state.popularPlaces, onPlaceClicked
            )
        }
        verticalMargin(40.dp)

        stickyHeader {

            when (val categories = state.categories) {
                is StateData.Failed -> Unit
                is StateData.Loading -> Unit
                is StateData.Success -> {
                    DatePickScrollableTabRow(
                        edgePadding = 20.dp,
                        backgroundColor = MaterialTheme.colors.surface,
                        contentColor = MaterialTheme.colors.onSurface,
                        selectedTabIndex = selectedCategoryIndex,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier.tabIndicatorOffset(tabPositions[selectedCategoryIndex]),
                                color = MaterialTheme.colors.secondary,
                                height = 4.dp
                            )
                        }
                    ) {
                        categories.data.forEachIndexed { index, categoryName ->
                            Tab(
                                modifier = Modifier.height(56.dp),
                                selected = selectedCategoryIndex == index,
                                onClick = {
                                    selectedCategoryIndex = index
                                    event(HomeViewModelDelegate.Event.SelectTab(categoryName))
                                },
                                text = {
                                    Text(text = categoryName)
                                }
                            )
                        }
                    }
                }
            }

        }

        homeCategoryContents(selectedRecommendedPlaces)

    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}