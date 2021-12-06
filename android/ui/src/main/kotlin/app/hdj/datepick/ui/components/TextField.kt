package app.hdj.datepick.ui.components

import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TextFieldDefaults.material3TextFieldColors(
    textColor: Color = LocalContentColor.current.copy(LocalContentAlpha.current),
    disabledTextColor: Color = textColor.copy(ContentAlpha.disabled),
    backgroundColor: Color = MaterialTheme.colorScheme.onSurface.copy(
        alpha = 0.05f
    ),
    cursorColor: Color = MaterialTheme.colorScheme.primary,
    errorCursorColor: Color = MaterialTheme.colorScheme.error,
    focusedIndicatorColor: Color =
        MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
    unfocusedIndicatorColor: Color =
        MaterialTheme.colorScheme.onSurface.copy(alpha = UnfocusedIndicatorLineOpacity),
    disabledIndicatorColor: Color = unfocusedIndicatorColor.copy(alpha = ContentAlpha.disabled),
    errorIndicatorColor: Color = MaterialTheme.colorScheme.error,
    leadingIconColor: Color =
        MaterialTheme.colorScheme.onSurface.copy(alpha = IconOpacity),
    disabledLeadingIconColor: Color = leadingIconColor.copy(alpha = ContentAlpha.disabled),
    errorLeadingIconColor: Color = leadingIconColor,
    trailingIconColor: Color =
        MaterialTheme.colorScheme.onSurface.copy(alpha = IconOpacity),
    disabledTrailingIconColor: Color = trailingIconColor.copy(alpha = ContentAlpha.disabled),
    errorTrailingIconColor: Color = MaterialTheme.colorScheme.error,
    focusedLabelColor: Color =
        MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
    unfocusedLabelColor: Color = MaterialTheme.colorScheme.onSurface.copy(
        ContentAlpha.medium
    ),
    disabledLabelColor: Color = unfocusedLabelColor.copy(ContentAlpha.disabled),
    errorLabelColor: Color = MaterialTheme.colorScheme.error,
    placeholderColor: Color = MaterialTheme.colorScheme.onSurface.copy(
        ContentAlpha.medium
    ),
    disabledPlaceholderColor: Color = placeholderColor.copy(ContentAlpha.disabled)
): TextFieldColors = textFieldColors(
    textColor,
    disabledTextColor,
    backgroundColor,
    cursorColor,
    errorCursorColor,
    focusedIndicatorColor,
    unfocusedIndicatorColor,
    disabledIndicatorColor,
    errorIndicatorColor,
    leadingIconColor,
    disabledLeadingIconColor,
    errorLeadingIconColor,
    trailingIconColor,
    disabledTrailingIconColor,
    errorTrailingIconColor,
    focusedLabelColor,
    unfocusedLabelColor,
    disabledLabelColor,
    errorLabelColor,
    placeholderColor,
    disabledPlaceholderColor
)

@Composable
fun TextFieldDefaults.material3OutlineTextFieldColors(
    textColor: Color = LocalContentColor.current.copy(LocalContentAlpha.current),
    disabledTextColor: Color = textColor.copy(ContentAlpha.disabled),
    backgroundColor: Color = Color.Transparent,
    cursorColor: Color = MaterialTheme.colorScheme.primary,
    errorCursorColor: Color = MaterialTheme.colorScheme.error,
    focusedIndicatorColor: Color =
        MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
    unfocusedIndicatorColor: Color =
        MaterialTheme.colorScheme.onSurface.copy(alpha = UnfocusedIndicatorLineOpacity),
    disabledIndicatorColor: Color = unfocusedIndicatorColor.copy(alpha = ContentAlpha.disabled),
    errorIndicatorColor: Color = MaterialTheme.colorScheme.error,
    leadingIconColor: Color =
        MaterialTheme.colorScheme.onSurface.copy(alpha = IconOpacity),
    disabledLeadingIconColor: Color = leadingIconColor.copy(alpha = ContentAlpha.disabled),
    errorLeadingIconColor: Color = leadingIconColor,
    trailingIconColor: Color =
        MaterialTheme.colorScheme.onSurface.copy(alpha = IconOpacity),
    disabledTrailingIconColor: Color = trailingIconColor.copy(alpha = ContentAlpha.disabled),
    errorTrailingIconColor: Color = MaterialTheme.colorScheme.error,
    focusedLabelColor: Color =
        MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
    unfocusedLabelColor: Color = MaterialTheme.colorScheme.onSurface.copy(
        ContentAlpha.medium
    ),
    disabledLabelColor: Color = unfocusedLabelColor.copy(ContentAlpha.disabled),
    errorLabelColor: Color = MaterialTheme.colorScheme.error,
    placeholderColor: Color = MaterialTheme.colorScheme.onSurface.copy(
        ContentAlpha.medium
    ),
    disabledPlaceholderColor: Color = placeholderColor.copy(ContentAlpha.disabled)
): TextFieldColors = outlinedTextFieldColors(
    textColor,
    disabledTextColor,
    backgroundColor,
    cursorColor,
    errorCursorColor,
    focusedIndicatorColor,
    unfocusedIndicatorColor,
    disabledIndicatorColor,
    errorIndicatorColor,
    leadingIconColor,
    disabledLeadingIconColor,
    errorLeadingIconColor,
    trailingIconColor,
    disabledTrailingIconColor,
    errorTrailingIconColor,
    focusedLabelColor,
    unfocusedLabelColor,
    disabledLabelColor,
    errorLabelColor,
    placeholderColor,
    disabledPlaceholderColor
)