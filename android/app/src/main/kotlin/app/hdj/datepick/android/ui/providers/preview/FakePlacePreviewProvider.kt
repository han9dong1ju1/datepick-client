package app.hdj.datepick.android.ui.providers.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.model.place.Place
import kotlin.random.Random

class FakePlacePreviewProvider : PreviewParameterProvider<List<Place>> {
    override val values: Sequence<List<Place>>
        get() = sequenceOf(
            listOf(
                object : Place {
                    override val id: Long = Random.nextLong()
                    override val kakaoId: Long = 13559837
                    override val name: String = "삼청동수제비"
                    override val address: String = "서울 종로구 삼청로 101-1 (우)03049"
                    override val latitude: Double = 37.5844951
                    override val longitude: Double = 126.9818969
                    override val rating: Double = 4.3
                    override val isPicked: Boolean = false
                    override val photos: List<String> = listOf()
                }
            )
        )
}