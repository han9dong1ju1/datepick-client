package app.hdj.datepick.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onBackground.copy(0.05f))
            .fillMaxWidth()
            .height(1.dp)
            .then(modifier)
    )
}