package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.ui.utils.*

@Composable
fun Header(
    text: String,
    moreButtonText: String? = null,
    onMoreButtonClicked: () -> Unit = {}
) {

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (headerRef, moreButton) = createRefs()

        Text(
            text = text,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.constrainAs(
                headerRef,
                t2t(margin = 20.dp) + b2b(margin = 20.dp) + fillTo(
                    tailTo = if (moreButtonText == null) null else moreButton,
                    startMargin = 20.dp
                )
            )
        )

        if (moreButtonText != null) {

            TextButton(
                onClick = onMoreButtonClicked,
                modifier = Modifier.constrainAs(moreButton, e2e(margin = 20.dp) + t2t() + b2b())
            ) {
                Text(text = moreButtonText)
            }

        }


    }

}

fun LazyListScope.header(text: String) {
    item {
        Header(text)
    }
}

fun LazyListScope.moreButtonHeader(
    text: String,
    moreButtonText: String,
    onMoreButtonClicked: () -> Unit
) {
    item { Header(text, moreButtonText, onMoreButtonClicked) }
}