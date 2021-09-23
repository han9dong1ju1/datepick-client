package app.hdj.datepick.ui.animation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut


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
            easing = FastOutLinearInEasing
        )
    ) + scaleOut(
        animationSpec = tween(210, 90, easing = LinearOutSlowInEasing),
        targetScale = 0.92f)
