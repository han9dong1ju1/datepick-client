package app.hdj.datepick.android.ui.screens.others.createCourse

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun CreateCourseScreen(
    courseId: String? = null,
    vm: CreateCourseViewModelDelegate = hiltViewModel<CreateCourseViewModel>()
) {

    val (state, effect, event) = vm.extract()

//    state.places.forEach {
//        googleMapState.addMarker(markerOptions {
//            position(LatLng(it.latitude, it.longitude))
//        })
//    }

}

@Composable
@Preview
fun CreateCourseScreenPreview() {
    BaseTheme {
        CreateCourseScreen(vm = fakeCreateCourseViewModel())
    }
}