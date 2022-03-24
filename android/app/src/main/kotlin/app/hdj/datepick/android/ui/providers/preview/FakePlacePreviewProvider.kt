package app.hdj.datepick.android.ui.providers.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.model.place.Place
import kotlin.random.Random

class FakePlacePreviewProvider : PreviewParameterProvider<List<Place>> {
    override val values: Sequence<List<Place>>
        get() = sequenceOf(
            (0..5).map {
                Place(
                    id = Random.nextLong(),
                    category = Place.Category(
                        category = "음식점",
                        type = "한식 ",
                        subtype = "수제비 ",
                    ),
                    kakaoId = Random.nextLong(),
                    name = "삼청동수제비",
                    address = "서울 종로구 삼청로 101 - 1(우)03049",
                    latitude = 37.5844951,
                    longitude = 126.9818969,
                    rating = 4.3,
                    isPicked = false,
                    photo =
                    if (Random.nextBoolean())
                        "https://postfiles.pstatic.net/MjAyMTAyMjNfMjQ5/MDAxNjE0MDQ4NDEwMDYy.JCXTJWhG8eUrNQjRkKtJpB7C3fDc5wyMm66xiG7sCcIg.Dxlm9hUXzUgHSxq4e0bz40d3nxMthzXMffnvyDUCyD0g.JPEG.hyonikim/IMG_1835.jpg?type=w773"
                    else null
                )
            }
        )
}