package app.hdj.datepick.android.ui.components.searchbox

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBoxItem(
    text : String,
    onClicked: () -> Unit,
) {
    Surface(
        modifier = Modifier.height(40.dp),
        onClick = onClicked,
        border = BorderStroke(1.dp, MaterialTheme.colors.onBackground.copy(0.05f)),
        shape = RoundedCornerShape(100.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = text, style = MaterialTheme.typography.subtitle2
            )
        }
    }
}


@Composable
fun <T> SearchBoxDropdownItem(
    initialValue: T,
    items: List<T>,
    dropdownItemText: (T) -> String,
    onItemClicked: (T) -> Unit,
) {

    var isExpanded by remember { mutableStateOf(false) }

    Box {

        Surface(
            onClick = {
                isExpanded = !isExpanded
            },
            border = BorderStroke(1.dp, MaterialTheme.colors.onBackground.copy(0.05f)),
            shape = RoundedCornerShape(100.dp)
        ) {
            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 6.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dropdownItemText(initialValue), style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.width(4.dp))
                if (isExpanded) {
                    Icon(Icons.Rounded.ArrowDropUp, null, modifier = Modifier.size(20.dp))
                } else {
                    Icon(Icons.Rounded.ArrowDropDown, null, modifier = Modifier.size(20.dp))
                }
            }
        }

        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onItemClicked(item)
                        isExpanded = false
                    }
                ) {
                    Text(text = dropdownItemText(item))
                }
            }
        }

    }
}