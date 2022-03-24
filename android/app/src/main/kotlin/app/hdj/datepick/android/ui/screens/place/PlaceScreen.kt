package app.hdj.datepick.android.ui.screens.place

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.hdj.datepick.domain.model.place.Place
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
fun PlaceScreen(
    place: Place,
    navigator: DestinationsNavigator
) {
    PlaceScreenContent(place = place)
}

@Composable
@Destination(
    deepLinks = [
        DeepLink(uriPattern = "$DEEPLINK_URL/place/{placeId}"),
        DeepLink(uriPattern = "$EXTERNAL_DEEPLINK_URL/place/{placeId}"),
    ]
)
fun PlaceScreenFromDeepLink(
    placeId: Long,
    navigator: DestinationsNavigator
) {
    PlaceScreenContent(placeId = placeId)
}

@Composable
private fun PlaceScreenContent(
    place: Place? = null,
    placeId: Long? = null
) {

    LaunchedEffect(true) {
        if (placeId != null) {}
        else if (place != null) {

        }
    }

    BaseScaffold(
        topBar = {
            InsetTopBar(
                navigationIcon = { TopAppBarBackButton() },
                title = {}
            )
        }
    ) {

    }
}