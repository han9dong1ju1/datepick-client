package app.hdj.datepick.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun BigTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text,
        modifier,
        color,
        TextUnit.Unspecified,
        null,
        null,
        null,
        letterSpacing,
        textDecoration,
        textAlign,
        lineHeight,
        overflow,
        true,
        maxLines,
        { },
        MaterialTheme.typography.h6
    )
}

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text,
        modifier,
        color,
        TextUnit.Unspecified,
        null,
        null,
        null,
        letterSpacing,
        textDecoration,
        textAlign,
        lineHeight,
        overflow,
        true,
        maxLines,
        { },
        MaterialTheme.typography.subtitle1
    )
}

@Composable
fun Subtitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text,
        modifier,
        color,
        TextUnit.Unspecified,
        null,
        null,
        null,
        letterSpacing,
        textDecoration,
        textAlign,
        lineHeight,
        overflow,
        true,
        maxLines,
        { },
        MaterialTheme.typography.subtitle2
    )
}

@Composable
fun Body(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text,
        modifier,
        color,
        TextUnit.Unspecified,
        null,
        null,
        null,
        letterSpacing,
        textDecoration,
        textAlign,
        lineHeight,
        overflow,
        true,
        maxLines,
        { },
        MaterialTheme.typography.subtitle2
    )
}