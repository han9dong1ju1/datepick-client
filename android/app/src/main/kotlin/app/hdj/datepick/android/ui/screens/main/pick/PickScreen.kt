package app.hdj.datepick.android.ui.screens.main.pick

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun PickScreen(vm: PickViewModelDelegate = hiltViewModel<PickViewModel>()) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

    val me = LocalMe.current

    val pagerState = rememberPagerState()

    val coroutineScope = rememberCoroutineScope()

    BaseScaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.navigationBars,
                    additionalBottom = 56.dp
                )
            )
        ,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text("Pick")
                    }
                )
                TabRow(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.Unspecified,
                    selectedTabIndex = pagerState.currentPage,
                    indicator = {
                        Box(
                            Modifier
                                .pagerTabIndicatorOffset(pagerState, it)
                                .height(2.dp)
                                .padding(horizontal = 10.dp)
                                .background(
                                    color = MaterialTheme.colors.secondary,
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
                                style = MaterialTheme.typography.subtitle1,
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
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(
                    FakePlacePreviewProvider().values.first() +
                            FakePlacePreviewProvider().values.first() +
                            FakePlacePreviewProvider().values.first()
                ) {
                    PlaceVerticalListItem(place = it, onPlaceClicked = {})
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