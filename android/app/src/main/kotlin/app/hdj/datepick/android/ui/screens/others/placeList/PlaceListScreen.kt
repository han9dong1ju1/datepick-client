package app.hdj.datepick.android.ui.screens.others.placeList

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.domain.onSucceed
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopAppBar
import app.hdj.datepick.ui.utils.extract

@Composable
fun PlaceListScreen(
    vm: PlaceListViewModelDelegate = hiltViewModel<PlaceListViewModel>(),
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(key1 = true) {
//        event(PlaceListViewModelDelegate.Event.Query(initialPlaceQuery))
    }

    BaseScaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            BaseTopAppBar(
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

            state.results.onSucceed { list ->
                items(list) { place ->
                    PlaceVerticalListItem(place, navController::openPlace)
                }
            }

        }

    }

}