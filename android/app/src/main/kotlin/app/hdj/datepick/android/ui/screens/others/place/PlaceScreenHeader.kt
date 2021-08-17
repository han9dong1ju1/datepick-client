package app.hdj.datepick.android.ui.screens.others.place

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.LargeTitleAndSubtitle

@Composable
fun PlaceScreenHeader(
    placeState : LoadState<Place>
) {
    Column(Modifier.fillMaxWidth()) {

        if (placeState.isStateSucceed()) {

            LargeTitleAndSubtitle(
                modifier = Modifier.padding(20.dp),
                title = placeState.data.name,
                subtitle = placeState.data.address
            )

            placeState.data.rating

        }

        else {

        }

    }
}