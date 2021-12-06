package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.ui.utils.*

@Composable
fun Header(
    title: String
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    leftSideUi: @Composable () -> Unit = {},
    rightSideUi: @Composable () -> Unit = {},
    onClick: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Unspecified,
        onClick = onClick
    ) {

        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            val (leftSideUiRef, textContainer, rightSideUiRef) = createRefs()

            Row(
                modifier = Modifier.constrainAs(
                    leftSideUiRef,
                    t2t(textContainer) + s2s() + b2b(textContainer)
                )
            ) {
                leftSideUi()
                Spacer(modifier = Modifier.width(20.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(
                        textContainer,
                        t2t() + b2b() + s2e(leftSideUiRef) + e2s(rightSideUiRef) + fillWidthToConstraint
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )

                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Row(
                modifier = Modifier.constrainAs(
                    rightSideUiRef,
                    t2t(textContainer) + e2e() + b2b(textContainer)
                )
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                rightSideUi()
            }

        }

    }

}