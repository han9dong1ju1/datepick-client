package app.hdj.datepick.android.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.data.model.place.Place

class FakePlaceProvider : PreviewParameterProvider<app.hdj.datepick.data.model.place.Place> {
    override val values: Sequence<app.hdj.datepick.data.model.place.Place>
        get() = sequenceOf(

        )
}