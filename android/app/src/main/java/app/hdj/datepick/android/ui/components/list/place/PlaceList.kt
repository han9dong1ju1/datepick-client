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