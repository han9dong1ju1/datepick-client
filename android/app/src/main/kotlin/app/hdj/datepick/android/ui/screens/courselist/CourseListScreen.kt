package app.hdj.datepick.android.ui.screens.courselist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.hdj.datepick.android.ui.components.list.CourseVerticalListItem
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onCourseClicked
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.presentation.courselist.CourseListScreenViewModel
import app.hdj.datepick.presentation.courselist.CourseListScreenViewModelDelegate
import app.hdj.datepick.presentation.courselist.CourseListScreenViewModelDelegate.Event.SearchCourses
import app.hdj.datepick.ui.components.BaseSwipeRefreshLayoutScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun CourseListScreen(
    navigator: DestinationsNavigator,
    courseQueryParams: CourseQueryParams
) {
    val vm = hiltViewModel<CourseListScreenViewModel>()

    LaunchedEffect(true) {
        vm.event(SearchCourses(courseQueryParams))
    }

    CourseListScreenContent(
        vm = vm,
        onCourseClicked = navigator.onCourseClicked
    )
}

@Composable
private fun CourseListScreenContent(
    vm: CourseListScreenViewModelDelegate,
    onCourseClicked: (Course) -> Unit
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    val lazyPagingItems = state.courses.collectAsLazyPagingItems()

    val refreshState = lazyPagingItems.loadState.refresh
    val appendState = lazyPagingItems.loadState.append

    val swipeRefreshState = rememberSwipeRefreshState(refreshState == LoadState.Loading)

    BaseSwipeRefreshLayoutScaffold(
        swipeRefreshState = swipeRefreshState,
        onRefresh = { lazyPagingItems.refresh() },
        topBar = {
            Column {
                InsetTopBar(
                    navigationIcon = { TopAppBarBackButton() },
                    enableDivider = false
                )
                Divider(color = MaterialTheme.colors.onBackground.copy(0.05f))
            }
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(it),
            state = lazyListState,
        ) {

            val itemCount = lazyPagingItems.itemCount

            if (itemCount != 0) {
                items(lazyPagingItems) { course ->
                    course?.let {
                        CourseVerticalListItem(course, onCourseClicked)
                    }
                }
            }

            if (refreshState == LoadState.Loading || appendState == LoadState.Loading) {
                item {

                }
            }

            if (refreshState is LoadState.Error || appendState is LoadState.Error) {
                item {

                }
            }

        }

    }

}