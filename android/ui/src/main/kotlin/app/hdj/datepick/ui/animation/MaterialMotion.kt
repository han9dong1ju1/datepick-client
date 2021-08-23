package app.hdj.datepick.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.dp

val materialTransitionZaxisIn =
    fadeIn(
        animationSpec = tween(
            210,
            90,
            easing = LinearOutSlowInEasing
        )
    ) + scaleIn(
        animationSpec = tween(210, 90, easing = LinearOutSlowInEasing),
        initialScale = 0.92f
    )

val materialTransitionZaxisOut =
    fadeOut(
        animationSpec = tween(
            90,
            easing = LinearOutSlowInEasing
        )
    ) + scaleOut(
        animationSpec = tween(210, 90, easing = LinearOutSlowInEasing),
        targetScale = 0.92f
    )

val materialTransitionYaxisIn =
    fadeIn(
        animationSpec = tween(
            210,
            90,
            easing = LinearOutSlowInEasing
        )
    ) + slideInVertically(
        animationSpec = tween(300,  easing = LinearOutSlowInEasing),
        initialOffsetY = { (it.dp - 10.dp).value.toInt() }
    )

val materialTransitionYaxisOut =
    fadeOut(
        animationSpec = tween(
            90,
            easing = LinearOutSlowInEasing
        )
    ) + slideOutVertically(
        animationSpec = tween(300,  easing = LinearOutSlowInEasing),
        targetOffsetY = { (it.dp + 10.dp).value.toInt() }
    )