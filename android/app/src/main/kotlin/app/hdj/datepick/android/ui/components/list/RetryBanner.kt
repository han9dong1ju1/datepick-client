package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.styles.BaseTheme

@Composable
fun RetryBanner(modifier: Modifier = Modifier, retry: () -> Unit) {
    Surface(
        modifier = modifier.padding(20.dp),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "항목을 불러올 수 없습니다.",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "인터넷 상태를 확인하고 다시 시도해주세요!",
                color = MaterialTheme.colors.onSurface.copy(0.5f),
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.align(Alignment.Start)) {
                BaseButton(
                    modifier = Modifier.height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.onBackground
                    ),
                    text = "다시 시도",
                    icon = Icons.Rounded.Refresh,
                    onClick = retry
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Composable
@Preview
fun RetryBannerPreview() {
    BaseTheme {
        RetryBanner {

        }
    }
}