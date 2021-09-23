package app.hdj.datepick.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun PopFromBottomVisibility(
    isVisible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = materialTransitionYaxisIn(),
        exit = materialTransitionYaxisOut(false),
        content = content
    )
}

@Composable
fun <S> SwitchFromBottomContent(
    targetState: S,
    content: @Composable AnimatedVisibilityScope.(targetState: S) -> Unit
) {
    AnimatedContent(
        targetState = targetState,
        transitionSpec = materialTransitionSpecYaxisPopFromBottom(),
        content = content
    )
}

