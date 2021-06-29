package app.hdj.datepick.ui.base

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
    ) {

        icon?.let {
            Icon(imageVector = it, contentDescription = text)
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
        }

        Text(text)
    }
}