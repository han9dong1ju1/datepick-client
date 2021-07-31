package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.*
import app.hdj.shared.client.domain.CourseQuery
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.entity.CourseMetadata
import app.hdj.shared.client.domain.entity.NoticeEvent
import app.hdj.shared.client.domain.entity.Place
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>(),
    onShowMoreCoursesClicked: (CourseQuery) -> Unit = {},
    onShowMorePlacesClicked: (PlaceQuery) -> Unit = {},
    onPlaceClicked: (Place) -> Unit = {},
    onCourseClicked: (CourseMetadata) -> Unit = {},
    onNoticeEventClicked: (NoticeEvent) -> Unit = {},
) {

    val (state, effect, event) = vm.extract()

    val featuredPagerState = rememberPagerState(pageCount = 0)

    LazyColumn(
        content = {

            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    HomeScreenFeaturedPage(state.featured)
                    HomeScreenTopBar()
                }
            }


        }
    )

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}