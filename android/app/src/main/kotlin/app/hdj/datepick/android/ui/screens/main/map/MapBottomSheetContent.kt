package app.hdj.datepick.android.ui.screens.main.map

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.list.PlaceHorizontalListItem
import app.hdj.datepick.android.ui.components.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.ui.animation.materialTransitionXaxisIn
import app.hdj.datepick.ui.animation.materialTransitionXaxisOut
import app.hdj.datepick.ui.components.*
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun MapBottomSheetContent(
    bottomSheetState: BottomSheetState
) {

    var currentSelectedPlaceTypePage by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
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
        }

        item {
            Header(title = "이 주변 장소들")

            Spacer(modifier = Modifier.height(10.dp))

            val tabs = listOf("음식점", "카페", "공방", "놀이공원", "보드게임", "영화관", "방탈출")

            CustomScrollableTabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = currentSelectedPlaceTypePage,
                backgroundColor = Color.Unspecified,
                divider = {},
                edgePadding = 20.dp
            ) {
                tabs.forEachIndexed { index, label ->
                    Tab(
                        selected = currentSelectedPlaceTypePage == index,
                        onClick = {
                            currentSelectedPlaceTypePage = index
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

            Spacer(modifier = Modifier.height(20.dp))

            AnimatedContent(
                targetState = currentSelectedPlaceTypePage,
                transitionSpec = {
                    materialTransitionXaxisIn(true) with materialTransitionXaxisOut(true)
                }
            ) {
                val list = remember {
                    FakePlacePreviewProvider().values.first()
                }

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(list) { index, place ->
                        if (index == 0) Spacer(modifier = Modifier.width(20.dp))
                        PlaceHorizontalListItem(place = place, onPlaceClicked = {})
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            HorizontalDivider()
        }

        item {
            Header(title = "이 주변 코스 추천")

        }

    }

}