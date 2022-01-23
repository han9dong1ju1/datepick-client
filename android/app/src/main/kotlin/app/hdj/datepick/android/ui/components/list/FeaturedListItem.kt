package app.hdj.datepick.android.ui.components.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.components.NetworkImage

@Composable
fun FeaturedListItem(
    featured: Featured,
    onFeaturedClicked: (Featured) -> Unit
) {

    Surface(
        onClick = { onFeaturedClicked(featured) },
        modifier = Modifier.fillMaxWidth().height(400.dp),
        color = Color.Black,
        shape = RoundedCornerShape(20.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            NetworkImage(
                modifier = Modifier.fillMaxSize().alpha(0.5f),
                url = featured.photoUrl
            )

            Column(
                modifier = Modifier.padding(20.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                CompositionLocalProvider(LocalContentColor provides Color.White) {
                    Text(featured.title, style = MaterialTheme.typography.h2)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.alpha(0.8f),
                        text = featured.description,
                        style = MaterialTheme.typography.body2
                    )
                }
            }

        }

    }

}