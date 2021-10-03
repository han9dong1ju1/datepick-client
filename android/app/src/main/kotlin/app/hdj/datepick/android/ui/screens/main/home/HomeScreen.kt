package app.hdj.datepick.android.ui.screens.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.components.list.FeaturedPagerItem
import app.hdj.datepick.android.ui.components.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openFeatured
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopAppBar
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.components.ViewPager
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
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

    BaseScaffold(
        topBar = {
            BaseTopAppBar()
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
            state = lazyListState,
            content = {

                verticalMargin(10.dp)

                item {
                    state.featured.onSucceedComposable {
                        ViewPager(
                            modifier = Modifier.fillMaxWidth(),
                            list = it,
                            itemSpacing = (-30).dp
                        ) { item, _ ->
                            FeaturedPagerItem(item, navController::openFeatured)
                        }
                    }
                }

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

            }
        )

    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    BaseTheme {
        HomeScreen(fakeHomeViewModel())
    }
}

@Composable
@Preview(
    showBackground = true, showSystemUi = true,
    device = Devices.NEXUS_10
)
fun HomeScreenTabletPreview() {
    BaseTheme {
        HomeScreen(fakeHomeViewModel())
    }
}