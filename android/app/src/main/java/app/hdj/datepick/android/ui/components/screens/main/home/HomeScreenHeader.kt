package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.ui.utils.b2b
import app.hdj.datepick.ui.utils.b2t
import app.hdj.datepick.ui.utils.plus
import app.hdj.datepick.ui.utils.s2s

@Composable
fun HomeLocationDropdown(
    modifier: Modifier = Modifier,
    location: String,
    onClick: () -> Unit,
    style: TextStyle
) {

    Row(modifier = modifier
        .clickable(remember { MutableInteractionSource() }, indication = null) { onClick() }) {

        Text(
            text = location,
            style = style,
            maxLines = 1
        )

        Spacer(modifier = Modifier.width(10.dp))

        IconButton(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .border(
                    1.dp,
                    color = MaterialTheme.colors.onSurface
                        .copy(alpha = 0.1f)
                        .compositeOver(MaterialTheme.colors.background),
                    shape = CircleShape
                )
                .size(24.dp),
            onClick = onClick
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = null
            )
        }

    }

}

@Composable
fun HomeCollapsedTitle(
    location: String,
    onLocationChangeClicked: () -> Unit
) {
    HomeLocationDropdown(
        location = location,
        style = LocalTextStyle.current,
        onClick = onLocationChangeClicked
    )
}

@Composable
fun HomeExpandedTitle(
    location: String,
    onLocationChangeClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            val (titleRef, headerRef) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(headerRef, b2t(titleRef, margin = 8.dp) + s2s(titleRef)),
                text = "찾을 위치",
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.caption
            )

            HomeLocationDropdown(
                modifier = Modifier.constrainAs(
                    titleRef,
                    b2b(margin = 20.dp) + s2s(margin = 20.dp)
                ),
                location = location,
                style = MaterialTheme.typography.h1,
                onClick = onLocationChangeClicked
            )
        }
    }
}