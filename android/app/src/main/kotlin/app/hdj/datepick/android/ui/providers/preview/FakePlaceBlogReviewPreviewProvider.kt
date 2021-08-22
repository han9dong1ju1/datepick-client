package app.hdj.datepick.android.ui.providers.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.model.place.BlogReview
import app.hdj.datepick.domain.model.place.Place
import kotlin.random.Random

class FakePlaceBlogReviewPreviewProvider : PreviewParameterProvider<List<BlogReview>> {
    override val values: Sequence<List<BlogReview>>
        get() = sequenceOf(
            listOf(
                object : BlogReview {
                    override val title: String = "주차가능한 삼청동 맛집 삼청동 수제비 다녀왔어요!"
                    override val content: String = "삼청동 맛집 삼청동 수제비 메뉴판은 이렇게 간단하구여! 아무래도 메인 메뉴인 수제비가 가장 맛있겠져?!!?"
                    override val url: String = "https://blog.naver.com/nau2001/221987183196"
                }
            )
        )
}