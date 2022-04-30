package app.hdj.datepick.android.ui.screens.kakaoplacesearch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.kakaoPlaceList
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.domain.onSucceed
import app.hdj.datepick.presentation.kakaoplacesearch.KakaoPlaceSearchScreenViewModel
import app.hdj.datepick.presentation.kakaoplacesearch.KakaoPlaceSearchScreenViewModelDelegate
import app.hdj.datepick.presentation.kakaoplacesearch.KakaoPlaceSearchScreenViewModelDelegate.Event
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun KakaoPlaceSearchScreen(
    navigator: DestinationsNavigator
) {

    KakaoPlaceSearchScreenContent(
        hiltViewModel<KakaoPlaceSearchScreenViewModel>(),
        onPlaceAdded = { navigator.popBackStack() }
    )

}

@Composable
private fun KakaoPlaceSearchScreenContent(
    vm: KakaoPlaceSearchScreenViewModelDelegate,
    onPlaceAdded: () -> Unit = {}
) {

    val (state, effect, event) = vm.extract()

    effect.collectInLaunchedEffect {
        when (it) {
            is KakaoPlaceSearchScreenViewModelDelegate.Effect.PlaceAdded -> {
                onPlaceAdded()
            }
        }
    }

    var searchText by remember { mutableStateOf("") }

    BaseScaffold(
        topBar = {
            InsetTopBar(
                title = { Text("새로운 장소 개척하기") },
                navigationIcon = { TopAppBarBackButton() },
                enableDivider = false
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {

            stickyHeader {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    shape = RoundedCornerShape(100.dp),
                    color = MaterialTheme.colors.onSurface.copy(0.01f)
                        .compositeOver(MaterialTheme.colors.background)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp, horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = searchText,
                            onValueChange = { searchText = it },
                            placeholder = { Text(text = "검색어를 입력해주세요.") },
                            maxLines = 1,
                            singleLine = true,
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    event(Event.Search(searchText))
                                }
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledBorderColor = Color.Unspecified,
                                focusedBorderColor = Color.Unspecified,
                                unfocusedBorderColor = Color.Unspecified
                            )
                        )

                        IconButton(onClick = {
                            if (searchText.isNotBlank()) {
                                event(Event.Search(searchText))
                            }
                        }) {
                            Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
                        }
                    }
                }
            }

            state.results?.onSucceed { results ->
                kakaoPlaceList(results) {
                    event(Event.AddPlace(it))
                }
            }
        }
    }

}