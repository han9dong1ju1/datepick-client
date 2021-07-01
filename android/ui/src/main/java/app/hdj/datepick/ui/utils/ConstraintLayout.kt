@file:Suppress("unused")

package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope

@Composable
fun ConstraintLayoutScope.t2t(
    ref: ConstrainedLayoutReference? = null,
    margin: Dp = 0.dp
): ConstrainScope.() -> Unit = {
    val reference = ref ?: parent
    top.linkTo(reference.top, margin)
}

@Composable
fun ConstraintLayoutScope.b2b(
    ref: ConstrainedLayoutReference? = null,
    margin: Dp = 0.dp
): ConstrainScope.() -> Unit = {
    val reference = ref ?: parent
    bottom.linkTo(reference.bottom, margin)
}

@Composable
fun ConstraintLayoutScope.s2s(
    ref: ConstrainedLayoutReference? = null,
    margin: Dp = 0.dp
): ConstrainScope.() -> Unit = {
    val reference = ref ?: parent
    start.linkTo(reference.start, margin)
}

@Composable
fun ConstraintLayoutScope.e2e(
    ref: ConstrainedLayoutReference? = null,
    margin: Dp = 0.dp
): ConstrainScope.() -> Unit = {
    val reference = ref ?: parent
    end.linkTo(reference.end, margin)
}

@Composable
fun ConstraintLayoutScope.match(
    ref: ConstrainedLayoutReference? = null,
    margin: Dp = 0.dp
): ConstrainScope.() -> Unit = {
    val reference = ref ?: parent
    end.linkTo(reference.end, margin)
    start.linkTo(reference.start, margin)
}

@Composable
fun ConstraintLayoutScope.t2b(
    ref: ConstrainedLayoutReference? = null,
    margin: Dp = 0.dp
): ConstrainScope.() -> Unit = {
    val reference = ref ?: parent
    top.linkTo(reference.bottom, margin)
}

@Composable
fun ConstraintLayoutScope.b2t(
    ref: ConstrainedLayoutReference? = null,
    margin: Dp = 0.dp
): ConstrainScope.() -> Unit = {
    val reference = ref ?: parent
    bottom.linkTo(reference.top, margin)
}


@Composable
fun ConstraintLayoutScope.s2e(
    ref: ConstrainedLayoutReference? = null,
    margin: Dp = 0.dp
): ConstrainScope.() -> Unit = {
    val reference = ref ?: parent
    start.linkTo(reference.end, margin)
}

@Composable
fun ConstraintLayoutScope.e2s(
    ref: ConstrainedLayoutReference? = null,
    margin: Dp = 0.dp
): ConstrainScope.() -> Unit = {
    val reference = ref ?: parent
    end.linkTo(reference.start, margin)
}

operator fun (ConstrainScope.() -> Unit).plus(constraint: ConstrainScope.() -> Unit): ConstrainScope.() -> Unit {
    val target = this
    return {
        target()
        constraint()
    }
}