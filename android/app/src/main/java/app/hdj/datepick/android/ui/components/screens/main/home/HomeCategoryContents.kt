package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.list.Header
import app.hdj.datepick.android.ui.components.list.place.PlaceListRow
import app.hdj.datepick.ui.utils.VerticalMargin
import app.hdj.datepick.ui.utils.verticalMargin
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.RecommendedPlaces

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
fun LazyListScope.homeCategoryContents(
    recommendedPlaces: StateData<List<RecommendedPlaces>>
) {

    verticalMargin()

    when (recommendedPlaces) {
        is StateData.Failed -> {
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .fillParentMaxWidth()
                )
            }
        }
        is StateData.Loading -> {
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .fillParentMaxWidth()
                )
            }
        }
        is StateData.Success -> {
            items(recommendedPlaces.data) {
                Column(modifier = Modifier.fillParentMaxWidth()) {
                    Header(it.title, "더보기") { }
                    PlaceListRow(StateData.Success(it.places)) {}
                    VerticalMargin(40.dp)
                }
            }
        }
    }

    verticalMargin(120.dp)

}