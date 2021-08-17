package app.hdj.datepick.android.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.domain.model.place.*

@Composable
fun PlaceList(place : Place) {



}

@Composable
fun PlaceListPreview(
    @PreviewParameter(FakePlacePreviewProvider::class) place : List<Place>
) {
    DatePickTheme {
        PlaceList(place = place.first())
    }
}