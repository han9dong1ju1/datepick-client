package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.ui.utils.*
import app.hdj.datepick.ui.utils.b2b

@Composable
fun LargeTitle(text: String) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            val (titleRef) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(titleRef, b2b(margin = 20.dp) + s2s(margin = 20.dp)),
                text = text,
                style = MaterialTheme.typography.h1, maxLines = 2
            )

        }
    }

}

@Composable
fun LargeTitleAndSubtitle(title: String, subtitle : String) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            val (titleRef, subtitleRef) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(titleRef, b2t(subtitleRef, margin = 10.dp) + s2s(subtitleRef)),
                text = title,
                style = MaterialTheme.typography.h1, maxLines = 2
            )

            Text(
                modifier = Modifier
                    .constrainAs(subtitleRef, b2b(margin = 20.dp) + s2s(margin = 20.dp)),
                text = subtitle,
                style = MaterialTheme.typography.subtitle2, maxLines = 2,
            )

        }
    }

}