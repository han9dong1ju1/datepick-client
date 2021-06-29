package app.hdj.datepick.ui.base

import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThemePickItem(
    onClick: () -> Unit
) {

    Card(onClick = onClick) {

    }

}