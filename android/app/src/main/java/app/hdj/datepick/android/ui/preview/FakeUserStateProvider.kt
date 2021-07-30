package app.hdj.datepick.android.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.User

class FakeUserStateProvider : PreviewParameterProvider<StateData<User>> {

    override val values = sequenceOf(
        StateData.Loading(),
        StateData.Failed<User>(null, null),
    )

}