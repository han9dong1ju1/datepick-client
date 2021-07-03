package app.hdj.datepick.android.ui.components.screens.others.place_list

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.hdj.datepick.android.ui.components.list.PlaceItem
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.showPlace
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.entity.Place

@Composable
fun PlaceListScreen(
    initialPlaceQuery: PlaceQuery,
    vm: PlaceListViewModelDelegate = hiltViewModel<PlaceListViewModel>(),
    onPlaceClicked: (Place) -> Unit
) {

    val (state, effect, event) = vm.extract()

    val list = state.places.collectAsLazyPagingItems()

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(key1 = true) {
        event(PlaceListViewModelDelegate.Event.Query(initialPlaceQuery))
    }

    DatePickScaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            DatePickTopAppBar(
                title = {
                    // TODO Search Bar
                },
                navigationIcon = {
                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, null)
                    }
                }
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            items(list) { place -> PlaceItem(place, onPlaceClicked) }
        }

    }

}