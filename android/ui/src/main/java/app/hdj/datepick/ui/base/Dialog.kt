package app.hdj.datepick.ui.base

import androidx.compose.runtime.*
import androidx.compose.ui.window.Dialog
import com.google.accompanist.swiperefresh.SwipeRefreshState

class DialogState(shown: Boolean) {

    var isShown by mutableStateOf(shown)

}

@Composable
fun rememberDialogState(initialState : Boolean) = remember { DialogState(initialState) }

@Composable
fun DatePickDialog(
    dialogState: DialogState = rememberDialogState(initialState = false)
) {

    if (dialogState.isShown) {

        Dialog(onDismissRequest = {
            dialogState.isShown = false
        }) {

        }

    }

}
