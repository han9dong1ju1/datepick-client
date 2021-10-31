package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.PlaceListWithHeader
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openWebUrl
import app.hdj.datepick.domain.mapOrNull
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.navigationBarsHeight

@Composable
fun PlaceDetailScreen(
    placeId: Long? = null,
    place: Place? = null,
    vm: PlaceDetailViewModelDelegate = hiltViewModel<PlaceDetailViewModel>(),
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

    val scrollState = rememberScrollState()

    LaunchedEffect(true) {
        if (place != null) {
            event(PlaceDetailViewModelDelegate.Event.ShowPassedPlace(place))
        } else {
            placeId?.let { event(PlaceDetailViewModelDelegate.Event.RequestPlace(it)) }
        }
    }

    BaseScaffold(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            Spacer(Modifier.height(10.dp))

            PlaceDetailScreenBlogReviewSection(blogReviewState = state.blogReviews) {
                navController.openWebUrl(it.url)
            }

            PlaceDetailScreenReviewSection {

            }

            Spacer(Modifier.height(10.dp))

            state.place.mapOrNull { it }?.let { it1 ->
                PlaceDetailScreenMapLocationSection(it1) {
                    navController.openWebUrl(it)
                }
            }

            Spacer(Modifier.height(10.dp))

            PlaceListWithHeader("주변에 있는 또다른 장소", state.nearbyPlaces)
            Spacer(Modifier.height(10.dp))

            PlaceListWithHeader("이 장소는 어때요?", state.similarPlaces)
            Spacer(Modifier.height(10.dp))

            Header(title = "이 장소가 포함된 코스")
            LazyRow(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

            }
            Spacer(Modifier.height(10.dp))

            Spacer(Modifier.navigationBarsHeight(additional = 20.dp))
        }

    }

}