package app.hdj.datepick.android.ui.components.screens.others.place

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.LargeTitleAndSubtitle
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.components.googlemap.GoogleMap
import app.hdj.datepick.ui.components.googlemap.rememberCameraUpdateState
import app.hdj.datepick.ui.components.googlemap.rememberMapUiSettings
import app.hdj.datepick.ui.components.googlemap.rememberMarkerOptionsState
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.isFirstItemScrolled
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.model.markerOptions

@Composable
fun PlaceScreen(
    placeId: Long? = null,
    place: Place? = null,
    vm: PlaceViewModelDelegate = hiltViewModel<PlaceViewModel>(),
) {

    val (state, effect, event) = fakePlaceViewModel().extract()

    val lazyListState = rememberLazyListState()

    val isHeaderScrolled = lazyListState.isFirstItemScrolled(limit = 200.dp - 56.dp)

    if (place != null) {
        event(PlaceViewModelDelegate.Event.ShowPassedPlace(place))
    } else {
        placeId?.let { event(PlaceViewModelDelegate.Event.RequestPlace(it)) }
    }


    val placeState = state.place

    DatePickScaffold(
        Modifier.fillMaxSize(),
        topBar = {
            DatePickTopAppBar(
                navigationIcon = { TopAppBarBackButton() },
                actions = {

                },
                title = {
                    AnimatedVisibility(visible = isHeaderScrolled) {
                        if (placeState.isStateSucceed()) Text(text = placeState.data.name)
                    }
                }
            )
        }
    ) {

        LazyColumn(
            Modifier.padding(top = it.calculateTopPadding())
        ) {

            item {
                Surface(modifier = Modifier.fillMaxWidth()) {
                    PlaceScreenHeader(placeState = placeState)
                }
            }

            item {
                Surface(modifier = Modifier.fillMaxWidth()) {
                    PlaceMapCard(placeState = placeState)
                }
            }

        }

    }

}

@Composable
@Preview
fun PlaceScreenPreview() {
    DatePickTheme {
        PlaceScreen(vm = fakePlaceViewModel())
    }
}