package app.hdj.datepick.android.ui.providers.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.model.featured.Featured
import kotlin.random.Random

class FakeFeaturedPreviewProvider : PreviewParameterProvider<List<Featured>> {
    override val values: Sequence<List<Featured>>
        get() = sequenceOf(
            listOf(
                object : Featured {
                    override val id: Long = 0
                    override val title: String = "서울 종로구 재밌는 데이트 코스 10선"
                    override val subtitle: String = "서울 종로구에서 한옥마을을 포함한 정갈한 데이트 코스들을 만나보세요!"
                    override val imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Bukchon_Hanok_Village_북촌_한옥마을_October_1_2020_15.jpg/600px-Bukchon_Hanok_Village_북촌_한옥마을_October_1_2020_15.jpg"
                    override val content: String = ""
                    override val isPinned: Boolean = true
                }
            )
        )
}