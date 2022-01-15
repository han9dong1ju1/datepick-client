package app.hdj.datepick.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TextRadioButton(
    text: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Box(modifier = Modifier.clickable { onSelect() }) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            RadioButton(
                selected = isSelected,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    unselectedColor = MaterialTheme.colors.onSurface.copy(0.7f)
                )
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = text,
                color = if (isSelected) MaterialTheme.colors.secondary
                else MaterialTheme.colors.onSurface.copy(0.7f)
            )
        }
    }
}

@Composable
fun TextRadioButtonGroup(
    modifier: Modifier = Modifier,
    state: TextRadioButtonGroupState = rememberTextRadioButtonGroupState(),
    texts: List<String>,
    onSelect: () -> Unit,
) {

    Column(modifier = modifier) {
        texts.forEachIndexed { index, text ->
            TextRadioButton(
                text,
                state.selectedIndex == index
            ) {
                state.selectedIndex = index
                onSelect()
            }
        }
    }

}

@Composable
fun rememberTextRadioButtonGroupState(initialSelectedIndex: Int = 0) = remember {
    TextRadioButtonGroupState(initialSelectedIndex)
}

@Stable
class TextRadioButtonGroupState(initialSelectedIndex: Int) {
    var selectedIndex by mutableStateOf(initialSelectedIndex)
}