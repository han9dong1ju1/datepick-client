package app.hdj.datepick.android.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.data.model.User

class FakeUserStateProvider : PreviewParameterProvider<app.hdj.datepick.domain.StateData<app.hdj.datepick.data.model.User>> {

    override val values = sequenceOf(
        app.hdj.datepick.domain.StateData.Loading(),
        app.hdj.datepick.domain.StateData.Failed<app.hdj.datepick.data.model.User>(null, null),
    )

}