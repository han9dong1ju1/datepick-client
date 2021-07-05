package app.hdj.datepick.android.ui.components.screens.others.search_place

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.StateData
import com.google.android.gms.maps.model.LatLng

@Composable
fun SearchPlaceScreen(vm: SearchPlaceViewModelDelegate = hiltViewModel<SearchPlaceViewModel>()) {

    val (state, effect, event) = vm.extract()

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

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
                title = {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        value = textFieldValue,
                        onValueChange = setTextFieldValue,
                        placeholder = {
                            Text(text = "검색어를 입력해주세요.")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Unspecified,
                            unfocusedBorderColor = Color.Unspecified
                        )
                    )
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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(item.name, style = MaterialTheme.typography.h6)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(item.description, style = MaterialTheme.typography.body1)

                            if (item.distanceAsString != null) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("${item.distanceAsString}", style = MaterialTheme.typography.body2)
                            }
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