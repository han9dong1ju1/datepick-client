package app.hdj.datepick.android.ui.components.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.android.ui.shimmer
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.animation.materialTransitionSpecYaxisPopFromBottom
import app.hdj.datepick.ui.components.Header

@Composable
fun PlaceListWithHeader(
    headerText: String,
    placesState: LoadState<List<Place>>
) {

    val navController = LocalAppNavController.current

    AnimatedContent(
        targetState = placesState,
        transitionSpec = materialTransitionSpecYaxisPopFromBottom()
    ) { state ->
        when (state) {
            is LoadState.Loading -> {
                PlaceListShimmerWithHeader(headerText)
            }
            is LoadState.Success -> {
                PlaceListWithHeader(headerText, state.data, navController::openPlace)
            }
            is LoadState.Failed -> {
                PlaceListWithHeader(headerText, state.cachedData, navController::openPlace)
            }
        }
    }

}

@Composable
private fun PlaceListShimmerWithHeader(headerText: String) {
    Surface(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()) {
            Header(title = headerText)
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)) {
                Spacer(modifier = Modifier.size(80.dp, 8.dp).shimmer())
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.size(100.dp, 14.dp).shimmer())
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.size(60.dp, 10.dp).shimmer())
            }
        }
    }
}

@Composable
private fun PlaceListWithHeader(
    headerText: String,
    places: List<Place>?,
    onPlaceClicked: (Place) -> Unit
) {
    if (!places.isNullOrEmpty()) {
        Surface(Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxWidth()) {
                Header(title = headerText)
                Column {
                    places.forEach { place ->
                        PlaceVerticalListItem(place = place, onPlaceClicked = onPlaceClicked)
                    }
                }
            }
        }
    }
}