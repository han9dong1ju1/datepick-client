package app.hdj.datepick.android.ui.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate.Event.ChangeStatusBarMode
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.StatusBarMode
import app.hdj.datepick.android.ui.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.android.ui.screens.openFeatured
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.styles.onSurface50
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.isFirstItemScrolled
import app.hdj.datepick.ui.utils.verticalMargin

private val TOP_FEATURED_PAGER_HEIGHT = 400.dp

@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    val navController = LocalAppNavController.current
    val appViewModel = LocalDatePickAppViewModel.current

    val isHeaderCollapsed = lazyListState.isFirstItemScrolled(limit = TOP_FEATURED_PAGER_HEIGHT / 3)

    remember(isHeaderCollapsed) {
        val mode = if (isHeaderCollapsed) {
            ChangeStatusBarMode(StatusBarMode.STATUS_BAR_SYSTEM_WITH_TRANSPARENCY)
        } else {
            ChangeStatusBarMode(StatusBarMode.STATUS_BAR_FORCE_WHITE)
        }
        appViewModel.event(mode)
        mode
    }

    DatePickScaffold(
        topBar = {
            HomeScreenTopBar(isHeaderCollapsed) {

            }
        },
        bottomBar = {
            Surface(modifier = Modifier
                .height(56.dp)
                .graphicsLayer {

                }) {

            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            content = {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(TOP_FEATURED_PAGER_HEIGHT)
                            .background(MaterialTheme.colors.onSurface50)
                    ) {
                        HomeScreenFeaturedHeader(state.featured, navController::openFeatured)
                    }
                }

                verticalMargin(10.dp)

                item {
                    Header(title = "이 장소들은 어때요?")
                    Column(Modifier.fillMaxWidth()) {
                        state.featuredPlaces.onSucceedComposable { list ->
                            list.forEach {
                                PlaceVerticalListItem(
                                    place = it,
                                    onPlaceClicked = navController::openPlace
                                )
                            }
                        }
                    }
                }

                verticalMargin(10.dp)

                val place = FakePlacePreviewProvider().values
                    .first()
                    .first()

                items((0..10).toList()) {
                    Text(
                        text = "$it", modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.openPlace(place) }
                            .padding(20.dp)
                    )
                }

            }
        )

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}

@Composable
@Preview(
    showBackground = true, showSystemUi = true,
    device = Devices.NEXUS_10
)
fun HomeScreenTabletPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}