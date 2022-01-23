package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.components.TextButton

@Composable
fun Header(
    title: String,
    moreButton: String? = null,
    onMoreButtonClicked: () -> Unit = {}
) {

    Box(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 10.dp).height(50.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        Row {
            Spacer(Modifier.width(10.dp))
            Text(
                title,
                style = MaterialTheme.typography.subtitle1
            )
        }

        if (moreButton != null) {
            TextButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = moreButton,
                onClick = onMoreButtonClicked
            )
        }

    }

}