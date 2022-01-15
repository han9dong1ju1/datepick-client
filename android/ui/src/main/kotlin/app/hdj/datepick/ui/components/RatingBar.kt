package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.styles.BaseTheme
import kotlin.math.roundToInt

@Composable
fun ImmutableRatingBar(
    modifier: Modifier = Modifier,
    rating: Float = 0f,
    max: Int = 5,
    ratedColor: Color = MaterialTheme.colors.secondary,
    unratedColor: Color = MaterialTheme.colors.onSurface.copy(0.1f),
    spacing: Dp = 0.dp,
    starSize: Dp = 10.dp
) {

    Row(modifier) {
        (1..max).forEach { index ->
            val icon = when (index) {
                rating.roundToInt() -> {
                    if (index > rating.toInt()) Icons.Rounded.StarHalf
                    else Icons.Rounded.Star
                }
                else -> {
                    Icons.Rounded.Star
                }
            }

            val tintColor = if (index <= rating.roundToInt()) {
                ratedColor
            } else {
                unratedColor
            }

            Icon(
                modifier = Modifier.size(starSize),
                imageVector = icon,
                contentDescription = null,
                tint = tintColor
            )

            if (index != max) Spacer(modifier = Modifier.width(spacing))
        }

    }

}


@Composable
@Preview(showBackground = true)
fun ImmutableRatingBarPreview() {
    BaseTheme {
        Column(Modifier.fillMaxWidth()) {
            ImmutableRatingBar(rating = 4.3f)
            ImmutableRatingBar(rating = 4.5f)
            ImmutableRatingBar(rating = 0.5f)
            ImmutableRatingBar(rating = 0f)
        }
    }
}