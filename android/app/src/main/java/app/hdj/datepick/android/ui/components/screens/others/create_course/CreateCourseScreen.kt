package app.hdj.datepick.android.ui.components.screens.others.create_course

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.DatePickGoogleMap
import app.hdj.datepick.ui.components.TitledLazyListScaffold
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.components.rememberGoogleMapState
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.model.markerOptions

@Composable
fun CreateCourseScreen(
    courseId: String? = null,
    vm: CreateCourseViewModelDelegate = hiltViewModel<CreateCourseViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val googleMapState = rememberGoogleMapState()

    state.places.forEach {
        googleMapState.addMarker(markerOptions {
            position(LatLng(it.lat, it.lng))
        })
    }

    TitledLazyListScaffold(
        navIcons = { TopAppBarBackButton() },
        title = { Text(text = "") },
        expandedTitle = {
            Column(modifier = Modifier.fillMaxWidth()) {

                DatePickGoogleMap(
                    googleMapState = googleMapState,
                    modifier = Modifier.height(160.dp)
                )

            }
        },
    ) {

    }

}

@Composable
@Preview
fun CreateCourseScreenPreview() {
    DatePickTheme {
        CreateCourseScreen(vm = fakeCreateCourseViewModel())
    }
}