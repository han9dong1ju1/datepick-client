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
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.utils.extract

@Composable
fun PlaceListScreen(
    vm: PlaceListViewModelDelegate = hiltViewModel<PlaceListViewModel>(),
) {

    val (state, effect, event) = vm.extract()


    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(key1 = true) {
//        event(PlaceListViewModelDelegate.Event.Query(initialPlaceQuery))
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



        }

    }

}