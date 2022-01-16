package app.hdj.datepick.android.ui.screens.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.verticalMargin
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

private val TOP_FEATURED_PAGER_HEIGHT = 400.dp

@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current
    val appViewModel = LocalDatePickAppViewModel.current

    BackHandler {
        navController.navigateRoute(AppNavigationGraph.ExitDialog)
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isContentLoading)

    BaseSwipeRefreshLayoutScaffold(
        swipeRefreshState = swipeRefreshState,
        topBar = {
            BaseTopBar(
                backgroundColor = Color.Unspecified,
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Rounded.Notifications, null)
                    }
                },
                title = {
                    Image(
                        modifier = Modifier.height(18.dp),
                        imageVector = DatePickIcons.Logo,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                        contentDescription = null
                    )
                }
            )
        },
        onRefresh = { event(HomeViewModelDelegate.Event.ReloadContents) },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            content = {

                item { HomeScreenFeaturedContents(state.featured) }

                verticalMargin(20.dp)

                item {
                    HomeScreenCourseContents(state = state.courses)
                }

                item {
                    HomeScreenNoticesContents(state = state.notices)
                }

                item {
                    HomeScreenPlacesContents(state = state.places)
                }

                items(1000) {
                    Text(it.toString())
                }

            }
        )


    }

}