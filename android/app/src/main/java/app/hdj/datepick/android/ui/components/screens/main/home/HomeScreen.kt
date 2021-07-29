package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.TitledLazyListScaffold
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.CourseQuery
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.entity.NoticeEvent
import app.hdj.shared.client.domain.entity.Place

@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>(),
    onShowMoreCoursesClicked: (CourseQuery) -> Unit = {},
    onShowMorePlacesClicked: (PlaceQuery) -> Unit = {},
    onPlaceClicked: (Place) -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
    onNoticeEventClicked: (NoticeEvent) -> Unit = {},
) {

    val (state, effect, event) = vm.extract()

    LazyColumn(
        content = {
            item {

                ConstraintLayout(modifier = Modifier.fillMaxSize()) {



                }

            }




        }
    )

}

@Composable
@Preview
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}