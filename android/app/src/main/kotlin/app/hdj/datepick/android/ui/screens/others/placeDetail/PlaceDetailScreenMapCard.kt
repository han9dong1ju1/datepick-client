package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Map
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openWebUrl
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.DatePickButton
import app.hdj.datepick.ui.components.googlemap.*
import app.hdj.datepick.ui.styles.shapes
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.model.markerOptions

@Composable
fun PlaceDetailMapCard(placeState: LoadState<Place>) {

    val navController = LocalAppNavController.current

    Surface(
        shape = shapes.medium,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(200.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            if (placeState.isStateSucceed()) {
                GoogleMapSnapshot(LatLng(placeState.data.latitude, placeState.data.longitude))
            }
            
            DatePickButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
                icon = Icons.Rounded.Map,
                text = "카카오맵으로 보기"
            ) {
                if (placeState.isStateSucceed()) {
                    val url = "https://place.map.kakao.com/${placeState.data.kakaoId}"
                    navController.openWebUrl(url)
                }
            }

        }

    }
}