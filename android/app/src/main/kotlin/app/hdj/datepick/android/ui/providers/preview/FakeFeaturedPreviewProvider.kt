package app.hdj.datepick.android.ui.providers.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.model.featured.Featured
import kotlin.random.Random

class FakeFeaturedPreviewProvider : PreviewParameterProvider<List<Featured>> {
    override val values: Sequence<List<Featured>>
        get() = sequenceOf(
            listOf(
                object : Featured {
                    override val id: String = Random.nextInt().toString()
                    override val title: String = "서울 종로구 재밌는 데이트 코스 10선"
                    override val description: String = "서울 종로구에서 한옥마을을 포함한 정갈한 데이트 코스들을 만나보세요!"
                    override val photoUrl: String = ""
                },

                object : Featured {
                    override val id: String = Random.nextInt().toString()
                    override val title: String = "서울 종로구 재밌는 데이트 코스 10선"
                    override val description: String = "서울 종로구에서 한옥마을을 포함한 정갈한 데이트 코스들을 만나보세요!"
                    override val photoUrl: String = ""
                },

                object : Featured {
                    override val id: String = Random.nextInt().toString()
                    override val title: String = "서울 종로구 재밌는 데이트 코스 10선"
                    override val description: String = "서울 종로구에서 한옥마을을 포함한 정갈한 데이트 코스들을 만나보세요!"
                    override val photoUrl: String = ""
                }
            )
        )
}