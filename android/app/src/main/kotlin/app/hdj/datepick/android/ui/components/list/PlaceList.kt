package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.NetworkImage

@Composable
fun PlaceHorizontalList(
    list: List<Place>,
    onPlaceClicked: (Place) -> Unit
) {
    LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 20.dp)) {
        itemsIndexed(list) { index, place ->
            PlaceHorizontalListItem(place, onPlaceClicked)
            if (list.lastIndex != index) Spacer(Modifier.width(20.dp))
        }
    }
}

@Composable
fun PlaceHorizontalListItem(
    place: Place,
    onPlaceClicked: (Place) -> Unit
) {
    Surface(
        onClick = { onPlaceClicked(place) },
        modifier = Modifier.fillMaxWidth().size(200.dp, 220.dp),
        color = if (place.photo != null) Color.Black else MaterialTheme.colors.surface,
        shape = RoundedCornerShape(20.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            NetworkImage(
                modifier = Modifier.fillMaxSize().alpha(0.3f),
                url = place.photo
            )

            Column(modifier = Modifier.padding(20.dp)) {
                CompositionLocalProvider(LocalContentColor provides if (place.photo != null) Color.White else MaterialTheme.colors.onSurface) {
                    Text(place.name, style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.alpha(0.8f),
                        text = place.address,
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.alpha(0.6f),
                        text = place.category.typeAsString,
                        style = MaterialTheme.typography.caption
                    )
                }
            }

        }

    }
}