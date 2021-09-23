package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openWebUrl
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.animation.materialTransitionSpecYaxisPopFromBottom
import app.hdj.datepick.ui.animation.materialTransitionYaxisIn
import app.hdj.datepick.ui.animation.materialTransitionYaxisOut
import app.hdj.datepick.ui.components.googlemap.GoogleMapSnapshot
import app.hdj.datepick.ui.styles.shapes
import com.google.android.gms.maps.model.LatLng

@Composable
fun PlaceDetailMapCard(placeState: LoadState<Place>) {

    val navController = LocalAppNavController.current

    Surface(modifier = Modifier.fillMaxSize()) {

        Surface(
            onClick = {
                if (placeState.isStateSucceed()) {
                    val url = "https://place.map.kakao.com/${placeState.data.kakaoId}"
                    navController.openWebUrl(url)
                }
            },
            shape = shapes.medium,
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                .height(200.dp)
        ) {

            AnimatedContent(
                targetState = placeState,
                transitionSpec = materialTransitionSpecYaxisPopFromBottom()
            ) { state ->
                if (state.isStateSucceed()) {
                    GoogleMapSnapshot(LatLng(state.data.latitude, state.data.longitude))
                }
            }
        }
    }

}