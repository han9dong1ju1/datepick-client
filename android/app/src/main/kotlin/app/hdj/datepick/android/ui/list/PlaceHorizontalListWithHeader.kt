package app.hdj.datepick.android.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.android.utils.foldComposable
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.Header

@Composable
fun PlaceHorizontalListWithHeader(
    headerText: String,
    placesState: LoadState<List<Place>>
) {

    val navController = LocalAppNavController.current

    Surface(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth()) {
            Header(title = headerText)
            placesState.foldComposable(
                onSuccess = { places ->
                    Column {
                        places.forEach { place ->
                            PlaceVerticalListItem(place = place, onPlaceClicked = navController::openPlace)
                        }
                    }
                },
                onFailed = { cached, error ->
                    if (cached.isNullOrEmpty()) {

                    } else {
                        Column {
                            cached.forEach { place ->
                                PlaceVerticalListItem(place = place, onPlaceClicked = navController::openPlace)
                            }
                        }
                    }
                },
                onLoading = {

                }
            )
        }
    }

}