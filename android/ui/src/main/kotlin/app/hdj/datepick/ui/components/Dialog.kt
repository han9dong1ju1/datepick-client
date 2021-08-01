package app.hdj.datepick.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController

val emptyDialogScope = object : DialogScope {
    override fun dismiss() {

    }
}

@Composable
fun DialogScope(
    navController: NavController,
    content : @Composable DialogScope.() -> Unit
) = object : DialogScope {
    override fun dismiss() {
        navController.popBackStack()
    }
}.content()

interface DialogScope {
    fun dismiss()
}

class DialogState(shown: Boolean) {

    var isShown by mutableStateOf(shown)

}

@Composable
fun rememberDialogState(initialState: Boolean) = remember { DialogState(initialState) }

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun DatePickDialog(
    dialogState: DialogState = rememberDialogState(initialState = false),
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    content: @Composable DialogScope.() -> Unit
) {

    AnimatedVisibility(
        visible = dialogState.isShown,
        enter = expandIn(initialSize = { IntSize(it.width / 2, it.height / 2) }) + fadeIn(),
        exit = shrinkOut(targetSize = { IntSize(it.width / 2, it.height / 2) }) + fadeOut()
    ) {

        val onDismiss = {
            dialogState.isShown = false
        }

        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(dismissOnBackPress, dismissOnClickOutside)
        ) {
            content(
                object : DialogScope {
                    override fun dismiss() {
                        onDismiss()
                    }
                }
            )
        }

    }

}

@Suppress("unused")
@Composable
fun DialogScope.DialogTextContent(
    title: String,
    message: String
) {
    Spacer(modifier = Modifier.height(10.dp))

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
    }

    Spacer(modifier = Modifier.height(10.dp))
}

data class DialogButtonProperties(
    val text: String,
    val isEnabled: Boolean = true,
    val onClick: () -> Unit = {}
)

@Suppress("unused")
@Composable
fun DialogScope.DialogContent(content: @Composable BoxScope.() -> Unit) {
    Spacer(modifier = Modifier.height(10.dp))
    Box(modifier = Modifier.fillMaxWidth()) {
        content()
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun DialogScope.DialogButtonContent(
    okButton: DialogButtonProperties,
    cancelButton: DialogButtonProperties? = null
) {


    Spacer(modifier = Modifier.height(20.dp))

    Row {

        if (cancelButton != null) {

            DatePickUnAccentButton(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                text = cancelButton.text
            ) {
                dismiss()
                cancelButton.onClick()
            }

            Spacer(modifier = Modifier.width(10.dp))
        }

        DatePickCTAButton(
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            text = okButton.text
        ) {
            dismiss()
            okButton.onClick()
        }

    }

}

@Composable
fun DialogScope.DialogUI(
    modifier : Modifier = Modifier,
    content: @Composable DialogScope.() -> Unit
) {
    Surface(modifier = modifier.width(320.dp), shape = MaterialTheme.shapes.large) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            content()
        }
    }
}