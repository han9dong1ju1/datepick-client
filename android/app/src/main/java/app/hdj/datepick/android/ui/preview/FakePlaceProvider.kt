package app.hdj.datepick.android.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.shared.client.domain.entity.Place

class FakePlaceProvider : PreviewParameterProvider<Place> {
    override val values: Sequence<Place>
        get() = sequenceOf(
            Place("", "이름")
        )
}