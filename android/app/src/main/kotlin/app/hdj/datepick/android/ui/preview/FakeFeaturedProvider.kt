package app.hdj.datepick.android.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.data.model.Featured

class FakeFeaturedProvider : PreviewParameterProvider<app.hdj.datepick.domain.StateData<List<app.hdj.datepick.data.model.Featured>>> {
    override val values: Sequence<app.hdj.datepick.domain.StateData<List<app.hdj.datepick.data.model.Featured>>>
        get() = sequenceOf(
            app.hdj.datepick.domain.StateData.Success(
                listOf(
                    app.hdj.datepick.data.model.Featured(
                        "",
                        "서울 종로 삼청동\n데이트 인기 코스 10 묶음",
                        "",
                        "서울 종로구, 중구"
                    )
                )
            ),
            app.hdj.datepick.domain.StateData.Loading()
        )
}