package app.hdj.datepick.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
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
                    unselectedColor = MaterialTheme.colorScheme.onSurface.copy(0.7f)
                )
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = text,
                color = if (isSelected) MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.onSurface.copy(0.7f)
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