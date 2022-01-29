package app.hdj.datepick.android.ui.screens.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.TextButton
import app.hdj.datepick.ui.styles.tertiary

@Composable
fun MainHomeLocationPermissionBanner(
    modifier: Modifier = Modifier,
    onPermissionRequested: () -> Unit,
    onIgnored: () -> Unit,
) {

    Surface(
        modifier = modifier.padding(20.dp),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colors.tertiary
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                )
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "주변 추천 받기",
                color = MaterialTheme.colors.onSecondary,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "위치권한을 허용하여 주변에 있는 장소와 데이트 코스에 대한 정보를 알아보세요!",
                color = MaterialTheme.colors.onSecondary.copy(0.5f),
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.align(Alignment.Start)) {

                BaseButton(
                    modifier = Modifier.height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.onSecondary.copy(alpha = 0.1f),
                        contentColor = MaterialTheme.colors.onSecondary
                    ),
                    text = "위치권한 허용",
                    icon = Icons.Rounded.Place,
                    onClick = onPermissionRequested
                )

                Spacer(modifier = Modifier.width(10.dp))

                BaseButton(
                    modifier = Modifier.height(48.dp),
                    text = "보지않음",
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = MaterialTheme.colors.onSecondary.copy(alpha = 0.8f),
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colors.onSecondary.copy(alpha = 0.2f)),
                    onClick = onIgnored
                )

            }

            Spacer(modifier = Modifier.height(16.dp))
        }

    }

}