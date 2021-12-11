package app.hdj.datepick.android.ui.screens.main.pick

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun PickScreen(vm: PickViewModelDelegate = hiltViewModel<PickViewModel>()) {

    val (state, effect, event) = vm.extract()

    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    val navController = LocalAppNavController.current

    val me = LocalMe.current

    val pagerState = rememberPagerState()

    val coroutineScope = rememberCoroutineScope()

    val colors = TopAppBarDefaults.smallTopAppBarColors()

    BaseScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column {
                InsetSmallTopAppBar(
                    title = { Text(text = "Pick") },
                    scrollBehavior = scrollBehavior,
                    colors = colors
                )
                TabRow(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = colors.containerColor(scrollBehavior.scrollFraction).value,
                    selectedTabIndex = pagerState.currentPage,
                    indicator = {
                        Box(
                            Modifier
                                .pagerTabIndicatorOffset(pagerState, it)
                                .height(2.dp)
                                .padding(horizontal = 10.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(
                                        topStart = 10.dp, topEnd = 10.dp
                                    )
                                )
                        )
                    },
                    divider = {}
                ) {
                    listOf("장소", "코스").forEachIndexed { index, label ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 14.dp)
                            )
                        }
                    }
                }
            }
        }
    ) {

        ViewPager(
            modifier = Modifier.fillMaxSize(),
            pagerState = pagerState,
            list = listOf(0, 1)
        ) { item, position ->
            if (position == 0) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    stickyHeader {

                    }

                    items(FakePlacePreviewProvider().values.first()) {
                        PlaceVerticalListItem(place = it, onPlaceClicked = {})
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                }
            }
        }

    }

}

@Composable
@Preview
fun PickScreenPreview() {
    BaseTheme {
        PickScreen(fakePickViewModel())
    }
}