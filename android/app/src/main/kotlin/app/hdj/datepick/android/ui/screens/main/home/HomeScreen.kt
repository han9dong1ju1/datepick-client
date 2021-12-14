package app.hdj.datepick.android.ui.screens.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.components.list.FeaturedPagerItem
import app.hdj.datepick.android.ui.components.list.FeaturedPagerItemShimmer
import app.hdj.datepick.android.ui.components.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.android.ui.screens.openFeatured
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.android.utils.ConnectionState
import app.hdj.datepick.android.utils.connectivityState
import app.hdj.datepick.android.utils.foldCrossfade
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.verticalMargin
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

private val TOP_FEATURED_PAGER_HEIGHT = 400.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current
    val appViewModel = LocalDatePickAppViewModel.current

    val scrollBehavior = remember {
        TopAppBarDefaults.pinnedScrollBehavior()
    }

    BackHandler {
        navController.navigateRoute(AppNavigationGraph.ExitDialog)
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isContentLoading)

    BaseSwipeRefreshLayoutScaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetSmallTopAppBar(
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Rounded.Notifications, null)
                    }
                },
                title = {
                    Image(
                        imageVector = DatePickIcons.Logo,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                        contentDescription = null
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        swipeRefreshState = swipeRefreshState,
        onRefresh = { event(HomeViewModelDelegate.Event.ReloadContents) },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            content = {

                verticalMargin(10.dp)

                item {
                    state.featured.foldCrossfade(
                        modifier = Modifier.animateItemPlacement(),
                        onLoading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                            ) {
                                FeaturedPagerItemShimmer()
                            }
                        },
                        onSuccess = {
                            ViewPager(
                                modifier = Modifier.fillMaxWidth(),
                                list = it,
                                itemSpacing = 10.dp,
                                contentPadding = PaddingValues(horizontal = 20.dp),
                                autoScrollDelay = 7000
                            ) { item, _ ->
                                FeaturedPagerItem(item, navController::openFeatured)
                            }
                        },
                        onFailed = { _, _ ->

                        }
                    )
                }

                item {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .animateItemPlacement()
                    ) {
                        state.featuredPlaces.onSucceedComposable { list ->
                            Header(title = "이 장소들은 어때요?")
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