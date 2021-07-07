@file:OptIn(ExperimentalMaterialApi::class)

package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.ui.utils.*

@Composable
fun SimpleList(
    title: String,
    subtitle: String,
    rightSideUi: @Composable () -> Unit = {},
    onClick: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Unspecified,
        onClick = onClick
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                val (titleRef, subtitleRef, rightSideUiRef) = createRefs()

                createVerticalChain(titleRef, subtitleRef, chainStyle = ChainStyle.Packed(0.0F))

                Text(
                    text = title, modifier = Modifier.constrainAs(
                        titleRef,
                        t2t() + b2t(titleRef) + fillTo(tailTo = rightSideUiRef) + fillToConstraint
                    ),
                    style = MaterialTheme.typography.h5
                )

                Text(
                    text = subtitle,
                    modifier = Modifier.constrainAs(
                        subtitleRef,
                        t2b(titleRef, margin = 6.dp) + fillTo(titleRef, rightSideUiRef)
                    ),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
                    style = MaterialTheme.typography.caption
                )

                Box(
                    modifier = Modifier.constrainAs(
                        rightSideUiRef,
                        t2t(titleRef) + e2e() + b2b(subtitleRef)
                    )
                ) {
                    rightSideUi()
                }

            }

            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f), thickness = 1.dp)

        }
    }

}