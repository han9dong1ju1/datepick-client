package app.hdj.datepick.android.ui.screens.main.myDate

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import app.hdj.datepick.android.ui.components.list.MyDateListItem
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.presentation.myDate.MyDateScreenViewModel
import app.hdj.datepick.presentation.myDate.MyDateScreenViewModelDelegate
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.BaseSwipeRefreshLayoutScaffold
import app.hdj.datepick.ui.components.BaseTopBar
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun MyDateScreen(vm: MyDateScreenViewModelDelegate = hiltViewModel<MyDateScreenViewModel>()) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

    val lazyListState = rememberLazyListState()

    val lazyPagingItems = state.myDateCourses.collectAsLazyPagingItems()

    val refreshState = lazyPagingItems.loadState.refresh
    val appendState = lazyPagingItems.loadState.append

    val swipeRefreshState = rememberSwipeRefreshState(refreshState == LoadState.Loading)

    BaseSwipeRefreshLayoutScaffold(
        swipeRefreshState = swipeRefreshState,
        indicatorPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.statusBars, additionalTop = 56.dp),
        onRefresh = { lazyPagingItems.refresh() },
        topBar = {
            Column {
                BaseTopBar(
                    title = {
                        Text("내 데이트")
                    },
                    actions = {
                        IconButton({
                            navController.navigateRoute(AppNavigationGraph.CreateCourse)
                        }) {
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
                                MyDateListItem(modifier = Modifier.fillMaxWidth(), course = item) {

                                }
                            }
                        }
                    }

                    lastItem = currentItem

                }
            }

            if (swipeRefreshState.isRefreshing || appendState == LoadState.Loading) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(20.dp).animateItemPlacement()) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp).align(Alignment.Center).animateItemPlacement()
                        )
                    }
                    Spacer(modifier = Modifier.navigationBarsHeight(20.dp))
                }
            }

            if (refreshState is LoadState.Error || appendState is LoadState.Error) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(20.dp).animateItemPlacement(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("목록을 가져오는데 오류가 발생했습니다.", style = MaterialTheme.typography.subtitle2)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("아래 버튼을 눌러 다시 시도해주세요.", style = MaterialTheme.typography.body1)
                        Spacer(modifier = Modifier.height(10.dp))
                        BaseButton(
                            onClick = { lazyPagingItems.retry() },
                            text = "다시 시도"
                        )
                    }
                    Spacer(modifier = Modifier.navigationBarsHeight(20.dp))
                }
            }

        }

    }

}

@Composable
private fun MyDateStickyHeader(currentItemDate: LocalDateTime?) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

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