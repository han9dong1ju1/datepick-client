package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.course.courseRowList
import app.hdj.datepick.android.ui.components.list.moreButtonHeader
import app.hdj.datepick.android.ui.components.list.notice_event.NoticeEventSmallItem
import app.hdj.datepick.android.ui.components.list.place.RankedPlaceItem
import app.hdj.datepick.ui.components.TitledLazyListScaffold
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.*
import app.hdj.shared.client.domain.CourseQuery
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.entity.Place
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>(),
    onShowMoreCoursesClicked: (CourseQuery) -> Unit = {},
    onShowMorePlacesClicked: (PlaceQuery) -> Unit = {},
    onPlaceClicked: (Place) -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
) {

    val (state, effect, event) = vm.extract()

    val coroutineScope = rememberCoroutineScope()

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

        moreButtonHeader("인기있는 장소들", "더보기") {}
        statefulItems(state.popularPlaces) { index, item ->
            if (index != 0) VerticalMargin()
            RankedPlaceItem(
                index + 1,
                item,
                onPlaceClicked
            )
        }

        verticalMargin(40.dp)

        statefulItem(state.noticeEvents) { noticeEvents ->

            val pagerState = rememberPagerState(pageCount = noticeEvents.size)

            LaunchedEffect(key1 = noticeEvents) {
                while (true) {
                    delay(5000)
                    if (pagerState.currentPage == pagerState.pageCount - 1) {
                        pagerState.animateScrollToPage(0)
                    } else pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }

            Column(modifier = Modifier.fillParentMaxWidth()) {
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth(),
                    state = pagerState,
                    itemSpacing = 15.dp
                ) {
                    NoticeEventSmallItem(noticeEvent = noticeEvents[it]) {

                    }
                }

                VerticalMargin(20.dp)

                HorizontalPagerIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    indicatorHeight = 6.dp,
                    indicatorWidth = 6.dp,
                    activeColor = MaterialTheme.colors.secondary,
                    inactiveColor = MaterialTheme.colors.onBackground.copy(0.1f),
                    spacing = 6.dp,
                    pagerState = pagerState
                )
            }
        }

        verticalMargin(40.dp)

        stickyHeader {
            when (val categoriesState = state.categories) {
                is StateData.Failed -> Unit
                is StateData.Loading -> Unit
                is StateData.Success -> {
                    HomeCategoryTab(categories = categoriesState.data) {
                        event(HomeViewModelDelegate.Event.SelectCategoryTab(it))
                    }
                }
            }
        }

        homeCategoryContents(
            recommendedPlaces = selectedRecommendedPlaces,
            onShowMorePlacesClicked = onShowMorePlacesClicked,
            onPlaceClicked = onPlaceClicked
        )

    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}