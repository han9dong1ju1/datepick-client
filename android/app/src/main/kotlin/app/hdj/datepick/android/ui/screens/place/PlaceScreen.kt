package app.hdj.datepick.android.ui.screens.place

import androidx.compose.runtime.Composable
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton

@Composable
fun PlaceScreen(placeId : Long?) {
    BaseScaffold(
        topBar = {
            BaseTopBar(
                navigationIcon = { TopAppBarBackButton() },
                title = {}
            )
        }
    ) {

    }
}