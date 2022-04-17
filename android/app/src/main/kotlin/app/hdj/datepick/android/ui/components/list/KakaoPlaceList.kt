package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.hdj.datepick.domain.model.place.KakaoPlaceSearch
import app.hdj.datepick.ui.components.ListItem

@Composable
fun KakaoPlaceListItem(
    modifier: Modifier = Modifier,
    kakaoPlace: KakaoPlaceSearch.Document,
    onClick: (KakaoPlaceSearch.Document) -> Unit
) {

    ListItem(
        modifier = modifier,
        title = kakaoPlace.placeName,
        subtitle = "${kakaoPlace.categoryName} | ${kakaoPlace.roadAddressName}",
        onClick = {
            onClick(kakaoPlace)
        }
    )

}

fun LazyListScope.kakaoPlaceList(
    kakaoPlaces: List<KakaoPlaceSearch.Document>,
    onClick: (KakaoPlaceSearch.Document) -> Unit
) {
    items(kakaoPlaces) {
        KakaoPlaceListItem(
            modifier = Modifier.fillMaxWidth().animateItemPlacement(),
            kakaoPlace = it,
            onClick = onClick
        )
    }
}