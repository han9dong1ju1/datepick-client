package app.hdj.datepick.android.ui.dialog.appupdate

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SystemUpdate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.android.ui.dialog.appupdate.AppUpdateViewModelDelegate.*
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalSnackBarPresenter
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.getActivity
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
                when (val result = state.appUpdateResult) {
                    is AppUpdateResult.Available -> {
                        context.getActivity()?.let {
                            event(Event.RequestUpdate(it, result))
                        }
                    }
                }
            },
            cancelButton = DialogButtonProperties("취소")
        )

    }
}

fun NavGraphBuilder.appUpdateDialog() {

    dialog(AppNavigationGraph.AppUpdateDialog.route) {

        val vm: AppUpdateViewModelDelegate = hiltViewModel<AppUpdateViewModel>()

        val (state, effect, event) = vm.extract()

        val snackbarPresenter = LocalSnackBarPresenter.current
        val navController = LocalAppNavController.current

        val dialogState = rememberDialogState(initialState = false)

        DialogScope(navController) {
            AppUpdateDialogUi(state = state, effect = effect, event = event)
        }

        val result = state.appUpdateResult

        LaunchedEffect(key1 = result) {
            when (result) {
                is AppUpdateResult.Available -> dialogState.show()
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
                    ) { event(Event.CompleteUpdate(result)) }
                }
                else -> Unit
            }
        }

    }

}

@Composable
@Preview
fun AppUpdateDialogPreview() {
    BaseTheme {
        val (s, e, ev) = fakeAppUpdateViewModel().extract()
        emptyDialogScope.AppUpdateDialogUi(s, e, ev)
    }
}