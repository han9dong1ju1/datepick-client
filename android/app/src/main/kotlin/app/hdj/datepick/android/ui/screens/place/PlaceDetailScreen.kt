package app.hdj.datepick.android.ui.screens.place

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.PlacesCardHeaderCarousel
import app.hdj.datepick.android.utils.*
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.presentation.place.PlaceDetailScreenViewModel
import app.hdj.datepick.presentation.place.PlaceDetailScreenViewModelDelegate
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.utils.DEEPLINK_URL
import app.hdj.datepick.utils.EXTERNAL_DEEPLINK_URL
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun PlaceDetailScreen(
    place: Place,
    navigator: DestinationsNavigator
) {
    val vm = hiltViewModel<PlaceDetailScreenViewModel>()

    LaunchedEffect(true) {
        vm.event(PlaceDetailScreenViewModelDelegate.Event.SetPlace(place))
    }

    PlaceDetailScreenContent(
        vm = vm,
        onPlaceClicked = navigator.onPlaceClicked,
        onMorePlaceListClicked = navigator.onMorePlaceListClicked
    )
}

@Composable
@Destination(
    deepLinks = [
        DeepLink(uriPattern = "$DEEPLINK_URL/place/{placeId}"),
        DeepLink(uriPattern = "$EXTERNAL_DEEPLINK_URL/place/{placeId}"),
    ]
)
fun PlaceDetailScreenFromDeepLink(
    placeId: Long,
    navigator: DestinationsNavigator
) {
    val vm = hiltViewModel<PlaceDetailScreenViewModel>()

    LaunchedEffect(true) {
        vm.event(PlaceDetailScreenViewModelDelegate.Event.LoadPlaceWithId(placeId))
    }

    PlaceDetailScreenContent(
        vm = vm,
        onPlaceClicked = navigator.onPlaceClicked,
        onMorePlaceListClicked = navigator.onMorePlaceListClicked
    )
}

@Composable
private fun PlaceDetailScreenContent(
    vm: PlaceDetailScreenViewModelDelegate,
    onPlaceClicked: (Place) -> Unit = {},
    onMorePlaceListClicked: (PlaceQueryParams) -> Unit,
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    BaseScaffold(
        topBar = {
            InsetTopBar(
                navigationIcon = { TopAppBarBackButton() },
                title = {

                },
                enableDivider = false
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {

            LoadStateAnimatedContent(
                modifier = Modifier.fillMaxWidth(),
                loadState = state.placeState,
                transitionSpec = { fadeIn() with fadeOut() },
                onLoading = {

                },
                onSuccess = { place ->
                    PlaceDetailScreenHeader(place = place)
                },
                onFailed = { data, throwable ->

                }
            )

            with(state.recommendedPlacesQueryResult) {
                PlacesCardHeaderCarousel(
                    "비슷한 장소",
                    result,
                    onPlaceClicked = onPlaceClicked,
                    onMoreClicked = { onMorePlaceListClicked(queryParams) }
                )
            }

        }
    }
}

@Composable
private fun PlaceDetailScreenHeader(place: Place) {



}