package app.hdj.datepick.android.ui.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.hdj.datepick.ui.components.CallToActionButton
import app.hdj.datepick.ui.components.UnAccentButton
import app.hdj.datepick.ui.components.dialog.DialogContent
import app.hdj.datepick.utils.Parcelable
import app.hdj.datepick.utils.Parcelize
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

@Parcelize
data class CheckboxSelectDialogResult(
    val selected: List<Long>
) : Parcelable

@Parcelize
data class CheckboxSelectDialogConfig(
    val title: String,
    val message: String,
    val items: List<Pair<Long, String>>,
    val selected: List<Long>
) : Parcelable

@Composable
@Destination(style = DestinationStyle.Dialog::class)
fun CheckboxSelectDialog(
    config: CheckboxSelectDialogConfig,
    resultBackNavigator: ResultBackNavigator<CheckboxSelectDialogResult>
) {

    var selectedItemIndex by remember {
        mutableStateOf(config.selected)
    }

    Dialog(
        onDismissRequest = { resultBackNavigator.navigateBack() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        content = {
            DialogContent(
                title = config.title,
                message = config.message,
                bottomContent = {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    ) {
                        itemsIndexed(config.items) { index, (id, text) ->
                            Box(modifier = Modifier
                                .clickable {
                                    selectedItemIndex =
                                        selectedItemIndex
                                            .toMutableList()
                                            .apply {
                                                if (id in selectedItemIndex) remove(id)
                                                else add(id)
                                            }
                                }
                                .padding(20.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = selectedItemIndex.contains(id),
                                        onCheckedChange = null
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(text = text)
                                }
                            }
                            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f))
                        }
                    }
                },
                positiveButton = {
                    CallToActionButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp), text = "확인"
                    ) {
                        resultBackNavigator.navigateBack(
                            CheckboxSelectDialogResult(selectedItemIndex)
                        )
                    }
                },
                negativeButton = {
                    UnAccentButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        text = "취소"
                    ) { resultBackNavigator.navigateBack() }
                }
            )
        }
    )

}