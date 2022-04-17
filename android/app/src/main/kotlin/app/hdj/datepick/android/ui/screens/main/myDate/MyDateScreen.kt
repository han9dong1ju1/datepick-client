package app.hdj.datepick.android.ui.screens.main.myDate

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import app.hdj.datepick.android.ui.components.list.MyDateListItem
import app.hdj.datepick.android.ui.destinations.CourseDetailCommentScreenDestination
import app.hdj.datepick.android.ui.destinations.CreateCourseThemeScreenDestination
import app.hdj.datepick.android.ui.destinations.MyDateScreenDestination
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onCourseClicked
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.presentation.mydate.MyDateScreenViewModel
import app.hdj.datepick.presentation.mydate.MyDateScreenViewModelDelegate
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.BaseSwipeRefreshLayoutScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
@Destination
fun MyDateScreen(
    navigator: DestinationsNavigator
) {
    MyDateScreenContent(
        onCreateDateClicked = { navigator.navigate(CreateCourseThemeScreenDestination) },
        onDateItemClicked = navigator.onCourseClicked,
        hiltViewModel<MyDateScreenViewModel>()
    )
}

@Composable
private fun MyDateScreenContent(
    onCreateDateClicked: () -> Unit = {},
    onDateItemClicked: (Course) -> Unit = {},
    vm: MyDateScreenViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    val lazyPagingItems = state.myDateCourses.collectAsLazyPagingItems()

    val refreshState = lazyPagingItems.loadState.refresh
    val appendState = lazyPagingItems.loadState.append

    val isRefreshing = remember(refreshState) { refreshState is LoadState.Loading }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    BaseSwipeRefreshLayoutScaffold(
        swipeRefreshState = swipeRefreshState,
        onRefresh = { lazyPagingItems.refresh() },
        topBar = {
            Column {
                InsetTopBar(
                    title = {
                        Text("내 데이트")
                    },
                    actions = {
                        IconButton(onCreateDateClicked) {
                            Icon(imageVector = Icons.Rounded.Add, null)
                        }
                    },
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

            var lastItem: Course? = null

            if (itemCount != 0) {
                (0..itemCount).forEach { index ->

                    val currentItem = lazyPagingItems.runCatching { peek(index) }.getOrNull()

                    val currentItemDate = currentItem?.meetAtInstant?.toLocalDateTime(TimeZone.currentSystemDefault())
                    val lastItemDate = lastItem?.meetAtInstant?.toLocalDateTime(TimeZone.currentSystemDefault())

                    if (
                        currentItemDate != null && currentItemDate.monthNumber != lastItemDate?.monthNumber
                        || index == 0
                    ) {
                        stickyHeader {
                            MyDateStickyHeader(currentItemDate)
                        }
                    }

                    item(key = currentItem?.id) {
                        Box(modifier = Modifier.padding(start = 80.dp, end = 10.dp).animateItemPlacement()) {
                            val item = lazyPagingItems.runCatching { get(index) }.getOrNull()
                            item?.let {
                                MyDateListItem(modifier = Modifier.fillMaxWidth(), course = item, onDateItemClicked)
                            }
                        }
                    }

                    lastItem = currentItem

                }
            }

            if (refreshState == LoadState.Loading || appendState == LoadState.Loading) {
                item { MyDateLoadLoadingBanner() }
            }

            if (refreshState is LoadState.Error || appendState is LoadState.Error) {
                item { MyDateLoadFailedBanner { lazyPagingItems.retry() } }
            }

        }

    }

}

@Composable
private fun LazyItemScope.MyDateLoadLoadingBanner() {
    Box(modifier = Modifier.fillMaxWidth().padding(20.dp).animateItemPlacement()) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp).align(Alignment.Center).animateItemPlacement()
        )
    }
    Spacer(modifier = Modifier.navigationBarsPadding())
    Spacer(modifier = Modifier.height(100.dp))
}

@Composable
private fun LazyItemScope.MyDateLoadFailedBanner(
    onRetry: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp).animateItemPlacement(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("목록을 가져오는데 오류가 발생했습니다.", style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(10.dp))
        Text("아래 버튼을 눌러 다시 시도해주세요.", style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.height(10.dp))
        BaseButton(
            onClick = onRetry,
            text = "다시 시도"
        )
    }
    Spacer(modifier = Modifier.navigationBarsPadding())
    Spacer(modifier = Modifier.height(100.dp))
}

@Composable
private fun LazyItemScope.MyDateStickyHeader(currentItemDate: LocalDateTime?) {
    Box(modifier = Modifier.fillMaxWidth()) {

        Column(
            modifier = Modifier.width(80.dp)
                .align(Alignment.CenterStart),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                currentItemDate?.year.toString(),
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground.copy(0.5f)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Surface(
                shape = RoundedCornerShape(100.dp),
                color = MaterialTheme.colors.secondary.copy(0.2f)
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    text = "${currentItemDate?.monthNumber}월",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.secondary
                )
            }
        }

    }
}