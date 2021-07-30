package app.hdj.datepick.android.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Featured
import app.hdj.shared.client.domain.entity.Place

class FakeFeaturedProvider : PreviewParameterProvider<StateData<List<Featured>>> {
    override val values: Sequence<StateData<List<Featured>>>
        get() = sequenceOf(
            StateData.Success(
                listOf(
                    Featured("", "서울 종로 삼청동\n데이트 인기 코스 10 묶음", "", "서울 종로구, 중구")
                )
            ),
            StateData.Loading()
        )
}