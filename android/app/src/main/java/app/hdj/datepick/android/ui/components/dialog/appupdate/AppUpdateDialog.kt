package app.hdj.datepick.android.ui.components.dialog.appupdate

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SystemUpdate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.android.ui.components.dialog.appupdate.AppUpdateViewModelDelegate.*
import app.hdj.datepick.android.ui.providers.LocalSnackBarPresenter
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import com.google.android.play.core.ktx.AppUpdateResult
import kotlinx.coroutines.flow.Flow

@Composable
fun DialogScope.AppUpdateDialogUi(
    state: State,
    effect: Flow<Effect>,
    event: (Event) -> Unit
) {

    val context = LocalContext.current

    DialogUI {

        DialogContent {

            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Center),
                imageVector = Icons.Rounded.SystemUpdate,
                contentDescription = null
            )

        }

        DialogTextContent(
            title = "앱 업데이트",
            message = "앱 업데이트가 있습니다.\n최신기능을 사용하려면 업데이트 해주세요.\n앱 업데이트는 백그라운드에서 설치됩니다."
        )

        DialogButtonContent(
            okButton = DialogButtonProperties("업데이트 하기") {

            },
            cancelButton = DialogButtonProperties("취소") {

            }
        )

    }
}

@Composable
fun AppUpdateDialog(vm: AppUpdateViewModelDelegate = hiltViewModel<AppUpdateViewModel>()) {

    val (state, effect, event) = vm.extract()

    val snackbarPresenter = LocalSnackBarPresenter.current

    val dialogState = rememberDialogState(initialState = false)

    DatePickDialog(dialogState) {
        AppUpdateDialogUi(state, effect, event)
    }

    when (val result = state.appUpdateResult) {
        is AppUpdateResult.Available -> dialogState.isShown = true
        is AppUpdateResult.InProgress -> {
            snackbarPresenter.showSnackBar(
                "앱 업데이트가 다운로드중입니다.",
                duration = SnackbarDuration.Short
            )
        }
        is AppUpdateResult.Downloaded -> {
            snackbarPresenter.showSnackBar(
                "앱 업데이트가 다운로드 되었습니다.\n다시 시작하면 업데이트가 설치됩니다.",
                "다시 시작하기",
                SnackbarDuration.Indefinite
            ) { result.completeUpdate() }
        }
        else -> Unit
    }

}

@Composable
@Preview
fun AppUpdateDialogPreview() {
    DatePickTheme {
        val (s, e, ev) = fakeAppUpdateViewModel().extract()
        emptyDialogScope.AppUpdateDialogUi(s, e, ev)
    }
}