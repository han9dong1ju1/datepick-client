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
            Icon(imageVector = Icons.Rounded.Place, null)
            Spacer(modifier = Modifier.height(12.dp))
            Text("주변에 있는 추천 장소들을 알아보세요.", style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "위치 권한을 허용하고 주변에 있는 추천 장소들을 알아보세요.",
                color = MaterialTheme.colors.onSecondary.copy(0.5f),
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.align(Alignment.End)) {

                BaseButton(
                    text = "보지않음",
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = MaterialTheme.colors.onSecondary.copy(alpha = 0.4f),
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colors.onSecondary.copy(alpha = 0.1f)),
                    onClick = onIgnored
                )

                Spacer(modifier = Modifier.width(10.dp))

                BaseButton(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = MaterialTheme.colors.tertiary
                    ),
                    text = "위치 권한 받기",
                    onClick = onPermissionRequested
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

    }

}