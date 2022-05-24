package app.hdj.datepick.ui.components.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogContent(
    title: String? = null,
    message: String? = null,
    topContent: (@Composable () -> Unit)? = null,
    bottomContent: (@Composable () -> Unit)? = null,
    negativeButton: (@Composable () -> Unit)? = null,
    positiveButton: (@Composable () -> Unit)? = null,
) {

    Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp)) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                topContent?.let {
                    it()
                    Spacer(modifier = Modifier.height(20.dp))
                }

                title?.let {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(it, style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(10.dp))
                }
                message?.let {
                    Text(it, style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface.copy(0.5f))
                }
            }
            
            bottomContent?.let {
                Column(modifier = Modifier.fillMaxWidth(), content = { it() })
            }

            Row(modifier = Modifier.padding(20.dp)) {
                negativeButton?.let {
                    Box(modifier = Modifier.weight(0.5f)) { negativeButton() }
                    Spacer(Modifier.width(10.dp))
                }

                positiveButton?.let {
                    Box(modifier = Modifier.weight(0.5f)) { positiveButton() }
                }
            }

        }

    }

}