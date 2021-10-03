package app.hdj.datepick.android.ui.screens.others.userProfileEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopAppBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.extract

@Composable
fun UserProfileEditScreen(vm: UserProfileEditViewModelDelegate = hiltViewModel()) {

    val navController = LocalAppNavController.current

    val (state, effect, event) = vm.extract()

    val me = LocalMe.current

    val scrollState = rememberScrollState()

    BaseScaffold(
        topBar = {
            BaseTopAppBar(
                navigationIcon = { TopAppBarBackButton() }
            )
        }
    ) {

        Column(
            Modifier
                .padding(it)
                .verticalScroll(scrollState)) {

        }

    }

}

@Preview
@Composable
fun UserProfileEditScreenPreview() {
    PreviewScope {
        UserProfileEditScreen(fakeUserProfileEditViewModel())
    }
}