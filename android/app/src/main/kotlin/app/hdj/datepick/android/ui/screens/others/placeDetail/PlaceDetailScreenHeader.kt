package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.animation.SwitchFromBottomContent
import app.hdj.datepick.ui.components.LargeTitleAndSubtitle
import app.hdj.datepick.ui.components.Shimmer
import timber.log.Timber

@Composable
fun PlaceDetailScreenHeader(
    placeState: LoadState<Place>
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomStart
        ) {
            Timber.d("PlaceHeader PlaceState : $placeState")
            SwitchFromBottomContent(targetState = placeState) { state ->

                when (state) {
                    is LoadState.Failed -> {
                        val cachedData = state.cachedData
                        if (cachedData != null) {
                            LargeTitleAndSubtitle(
                                title = cachedData.name,
                                subtitle = cachedData.address
                            )
                        }
                    }
                    is LoadState.Loading -> {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Shimmer(
                                Modifier
                                    .width(120.dp)
                                    .height(24.dp)
                            )
                            Spacer(Modifier.height(10.dp))
                            Shimmer(
                                Modifier
                                    .width(160.dp)
                                    .height(14.dp)
                            )
                        }
                    }
                    is LoadState.Success -> {

                        LargeTitleAndSubtitle(
                            title = state.data.name,
                            subtitle = state.data.address
                        )

                        state.data.rating

                    }
                }
            }
        }
    }
}