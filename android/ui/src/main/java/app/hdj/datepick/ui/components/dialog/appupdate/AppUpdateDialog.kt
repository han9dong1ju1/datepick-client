package app.hdj.datepick.ui.components.dialog.appupdate

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun AppUpdateDialog(vm: AppUpdateViewModelDelegate = hiltViewModel<AppUpdateViewModel>()) {

    val (state, effect, event) = vm.extract()



}

@Composable
@Preview
fun AppUpdateDialogPreview() {
    DatePickTheme {
        AppUpdateDialog(fakeAppUpdateViewModel())
    }
}