package app.hdj.datepick.android.ui.screens.main.home

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.components.list.FeaturedPagerItem
import app.hdj.datepick.android.ui.components.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openFeatured
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.components.InsetSmallTopAppBar
import app.hdj.datepick.ui.components.ViewPager
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.verticalMargin

private val TOP_FEATURED_PAGER_HEIGHT = 400.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    val navController = LocalAppNavController.current
    val appViewModel = LocalDatePickAppViewModel.current

    val scrollBehavior = remember {
        TopAppBarDefaults.pinnedScrollBehavior()
    }

    BaseScaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetSmallTopAppBar(title = {
                Image(
                    imageVector = DatePickIcons.Logo,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    contentDescription = null
                )
            }, scrollBehavior = scrollBehavior)
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            state = lazyListState,
            content = {

                verticalMargin(10.dp)

                item {
                    state.featured.onSucceedComposable {
                        ViewPager(
                            modifier = Modifier.fillMaxWidth(),
                            list = it,
                            itemSpacing = 10.dp,
                            contentPadding = PaddingValues(horizontal = 20.dp)
                        ) { item, _ ->
                            FeaturedPagerItem(item, navController::openFeatured)
                        }
                    }
                }

                stickyHeader { Header(title = "이 장소들은 어때요?") }

                item {
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