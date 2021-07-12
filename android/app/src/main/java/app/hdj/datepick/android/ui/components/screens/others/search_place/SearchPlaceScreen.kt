package app.hdj.datepick.android.ui.components.screens.others.search_place

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.StateData
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchPlaceScreen(vm: SearchPlaceViewModelDelegate = hiltViewModel<SearchPlaceViewModel>()) {

    val (state, effect, event) = vm.extract()

    val (textFieldValue, setTextFieldValue) = remember {
        mutableStateOf(TextFieldValue())
    }

    if (textFieldValue.text.isNotBlank()) {
        event(SearchPlaceViewModelDelegate.Event.Search(textFieldValue.text, 37.5665, 126.9780))
    }

    DatePickScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DatePickTopAppBar(
                title = { Text(text = "장소 검색하기") },
                navigationIcon = { TopAppBarBackButton() }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
        ) {
            stickyHeader {
                SearchBar(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    value = textFieldValue,
                    onValueChange = setTextFieldValue
                )
            }

            when (val result = state.searchResult) {
                is StateData.Failed -> {
                    if (result.cachedData != null) {

                    } else {

                    }
                }
                is StateData.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
                is StateData.Success -> {
                    items(result.data) { item ->
                        SimpleList(
                            title = item.name,
                            subtitle = item.description,
                            rightSideUi = {
                                if (item.distanceAsString != null) {
                                    Surface(
                                        shape = MaterialTheme.shapes.small,
                                        color = MaterialTheme.colors.secondary.copy(0.2f)
                                    ) {
                                        Text(
                                            "${item.distanceAsString}",
                                            modifier = Modifier.padding(
                                                horizontal = 10.dp,
                                                vertical = 2.dp
                                            ),
                                            style = MaterialTheme.typography.body1,
                                            color = MaterialTheme.colors.secondary
                                        )
                                    }
                                }
                            }
                        ) {

                        }
                    }
                }
                null -> {

                }
            }

        }
    }

}

@Composable
@Preview
fun SearchPlaceScreenPreview() {
    DatePickTheme {
        SearchPlaceScreen(fakeSearchPlaceViewModel())
    }
}