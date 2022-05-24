package app.hdj.datepick.android.ui.components.searchbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBox(
    text: String = "",
    onFocused: () -> Unit = {},
    onSearch: (String) -> Unit,
) {

    var searchText by remember { mutableStateOf(text) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.onBackground.copy(0.03f),
                        shape = RoundedCornerShape(100.dp)
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Rounded.Search, null)
                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .onFocusEvent {
                            if (it.isFocused) onFocused()
                        },
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text(text = "검색어를 입력해주세요.") },
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = { onSearch(searchText) }
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledBorderColor = Color.Unspecified,
                        focusedBorderColor = Color.Unspecified,
                        unfocusedBorderColor = Color.Unspecified
                    )
                )

            }
        }

        Spacer(modifier = Modifier.height(10.dp))

    }
}