package app.hdj.datepick.android.ui.components.list.place

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.utils.VerticalMargin
import app.hdj.datepick.ui.utils.statefulItems
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Place
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun PlaceListRow(
    stateData: StateData<List<Place>>,
    onPlaceClicked: (Place) -> Unit
) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        item { Spacer(modifier = Modifier.width(20.dp)) }

        statefulItems(
            stateData,
            error = {  },
            loading = {  }
        ) { _, item ->
            Row {
                PlaceCardItem(item, onPlaceClicked)
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class)
fun RankedPlaceHorizontalPager(
    modifier: Modifier,
    stateData: StateData<List<Place>>,
    onPlaceClicked: (Place) -> Unit
) {

    when (stateData) {
        is StateData.Failed -> {

        }
        is StateData.Loading -> {

        }
        is StateData.Success -> {
            Column(modifier = modifier) {
                val pages = stateData.data.chunked(5)
                val pagerState = rememberPagerState(pageCount = pages.size)

                HorizontalPager(
                    state = pagerState,
                    itemSpacing = (-100).dp
                ) { pageIndex ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        pages[pageIndex].forEachIndexed { index, place ->
                            if (index != 0) VerticalMargin()
                            RankedPlaceItem(
                                (pageIndex * 5) + (index + 1),
                                place,
                                onPlaceClicked
                            )
                        }
                    }
                }

                VerticalMargin(20.dp)

                HorizontalPagerIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    indicatorHeight = 6.dp,
                    indicatorWidth = 6.dp,
                    activeColor = MaterialTheme.colors.secondary,
                    spacing = 6.dp,
                    pagerState = pagerState
                )
            }
        }
    }

}