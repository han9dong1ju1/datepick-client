package app.hdj.datepick.android.ui.screens.main.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.ui.components.CustomScrollableTabRow
import app.hdj.datepick.ui.components.Tab
import app.hdj.datepick.ui.components.ViewPager
import app.hdj.datepick.ui.components.pagerCustomTabIndicatorOffset
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun MapBottomSheetContent(
    bottomSheetState : BottomSheetState
) {

    val pagerState = rememberPagerState()

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .statusBarsHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(visible = bottomSheetState.isCollapsed) {
                Box(
                    modifier = Modifier
                        .height(4.dp)
                        .width(50.dp)
                        .background(
                            MaterialTheme.colorScheme.onBackground.copy(0.1f),
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        val tabs = listOf("음식점", "카페", "공방", "놀이공원", "보드게임", "영화관", "방탈출")

        CustomScrollableTabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            indicator = {
                Box(
                    Modifier
                        .pagerCustomTabIndicatorOffset(pagerState, it)
                        .height(2.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(
                                topStart = 10.dp, topEnd = 10.dp
                            )
                        )
                )
            },
            backgroundColor = Color.Unspecified,
            divider = {},
            edgePadding = 20.dp
        ) {
            tabs.forEachIndexed { index, label ->
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

        val list = remember {
            FakePlacePreviewProvider().values.first()
        }

        ViewPager(
            modifier = Modifier.weight(1f),
            pagerState = pagerState,
            list = tabs
        ) { item, position ->
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(list) {
                    PlaceVerticalListItem(place = it, onPlaceClicked = {})
                }
            }
        }


    }

}