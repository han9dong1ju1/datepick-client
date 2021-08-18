package app.hdj.datepick.android.ui.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate.Event.ChangeStatusBarMode
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.StatusBarMode
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceDetail.ARGUMENT_PLACE
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.android.ui.screens.openFeatured
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedNavigationArgument
import app.hdj.datepick.android.ui.screens.others.placeDetail.PlaceNavigationArgument
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.putArguments

@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    val navController = LocalAppNavController.current
    val appViewModel = LocalDatePickAppViewModel.current

    remember(lazyListState.firstVisibleItemIndex) {
        val mode = if (lazyListState.firstVisibleItemIndex != 0) {
            ChangeStatusBarMode(StatusBarMode.STATUS_BAR_SYSTEM)
        } else {
            ChangeStatusBarMode(StatusBarMode.STATUS_BAR_FORCE_WHITE)
        }
        appViewModel.event(mode)
        mode
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        content = {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .background(Color.Black)
                ) {
                    HomeScreenFeaturedHeader(state.featured, navController::openFeatured)
                    HomeScreenTopBar()
                }
            }

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

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}