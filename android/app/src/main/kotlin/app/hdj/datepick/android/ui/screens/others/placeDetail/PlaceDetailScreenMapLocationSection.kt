package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.ListHeader
import app.hdj.datepick.ui.components.googlemap.GoogleMapSnapshot
import com.google.android.gms.maps.model.LatLng

@Composable
fun PlaceDetailScreenMapLocationSection(
    place: Place,
    onClicked: (String) -> Unit
) {

    ListHeader(title = "위치")
    GoogleMapCard(place, onClicked)
}

@Composable
private fun GoogleMapCard(
    place: Place,
    onClicked: (String) -> Unit
) {
    Surface(
        onClick = {
            val url = "https://place.map.kakao.com/${place.kakaoId}"
            onClicked(url)
        },
        modifier = GoogleMapCardModifier
    ) {
        GoogleMapSnapshot(location = LatLng(place.latitude, place.longitude))
    }
}

private val GoogleMapCardModifier = Modifier
    .fillMaxWidth()
    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
    .height(200.dp)