package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.ui.utils.*
import app.hdj.datepick.ui.utils.b2b

@Composable
fun LargeTitle(
    text: String,
    modifier: Modifier = Modifier,
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {

            val (titleRef) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(titleRef, b2b() + s2s()),
                text = text,
                style = MaterialTheme.typography.headlineLarge, maxLines = 2
            )

        }
    }

}

@Composable
fun LargeTitleAndSubtitle(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineLarge, maxLines = 1
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = subtitle,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge, maxLines = 1,
        )

    }
}