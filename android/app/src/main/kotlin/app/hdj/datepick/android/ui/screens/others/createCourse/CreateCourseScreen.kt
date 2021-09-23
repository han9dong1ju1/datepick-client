package app.hdj.datepick.android.ui.screens.others.createCourse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.TitledLazyListScaffold
import app.hdj.datepick.ui.components.TopAppBarBackButton
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

    TitledLazyListScaffold(
        navIcons = { TopAppBarBackButton() },
        title = { Text(text = "") },
        expandedTitle = {
            Column(modifier = Modifier.fillMaxWidth()) {

            }
        },
    ) {

    }

}

@Composable
@Preview
fun CreateCourseScreenPreview() {
    BaseTheme {
        CreateCourseScreen(vm = fakeCreateCourseViewModel())
    }
}