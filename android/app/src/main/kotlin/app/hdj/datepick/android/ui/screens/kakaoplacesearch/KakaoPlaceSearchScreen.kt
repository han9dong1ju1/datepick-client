package app.hdj.datepick.android.ui.screens.kakaoplacesearch

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.kakaoPlaceList
import app.hdj.datepick.android.ui.components.searchbox.SearchBox
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
                SearchBox(
                    onSearch = { query ->
                        if (query.isNotBlank()) {
                            event(Event.Search(query))
                        }
                    }
                )
                Divider(color = MaterialTheme.colors.onBackground.copy(0.05f))
            }

            state.results?.onSucceed { results ->
                kakaoPlaceList(results) {
                    event(Event.AddPlace(it))
                }
            }
        }
    }

}