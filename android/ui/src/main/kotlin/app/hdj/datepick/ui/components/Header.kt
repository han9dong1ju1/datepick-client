package app.hdj.datepick.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.styles.BaseTheme

@Composable
fun Header(title: String) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.padding(20.dp)) {
            Text(text = title, style = MaterialTheme.typography.h5)
        }
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_NO)
fun HeaderLightPreview() {
    BaseTheme {
        Header(title = "오늘의 코스")
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun HeaderDarkPreview() {
    BaseTheme {
        Header(title = "오늘의 코스")
    }
}