package app.hdj.datepick.android.ui.components.googlemap

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.model.place.Place
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.*
import com.google.maps.android.ui.IconGenerator

@Composable
fun PlaceGoogleMapMarker(
    markerState: MarkerState = rememberMarkerState(),
    place: Place,
    onMarkerClicked: (Place) -> Unit
) {
    val context = LocalContext.current

    Marker(
        state = markerState,
        icon = BitmapDescriptorFactory.fromBitmap(
            PlaceMarkerIconGenerator(context = context)
                .makeIcon(place)
        ),
        onInfoWindowLongClick = { it.showInfoWindow() },
        onClick = { onMarkerClicked(place); true }
    )

}

