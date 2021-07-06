@file:OptIn(ExperimentalMaterialApi::class)

package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
            .fillMaxWidth()
            .padding(20.dp),
        color = Color.Unspecified,
        onClick = onClick
    ) {
        ConstraintLayout(
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth()
        ) {

            val (titleRef, subtitleRef, rightSideUiRef) = createRefs()

            Text(
                text = title, modifier = Modifier
                    .constrainAs(
                        titleRef,
                        t2t() + s2s() + e2s(rightSideUiRef) + {
                            width = Dimension.fillToConstraints
                        }),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = subtitle,
                modifier = Modifier
                    .constrainAs(
                        subtitleRef,
                        t2b(
                            titleRef,
                            margin = 4.dp
                        ) + s2s(titleRef) + e2s(rightSideUiRef) + {
                            width = Dimension.fillToConstraints
                        }
                    ),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
                style = MaterialTheme.typography.body1
            )

            Box(
                modifier = Modifier.constrainAs(rightSideUiRef, t2t() + e2e() + b2b())
            ) {
                rightSideUi()
            }

        }
    }

}