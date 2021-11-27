package app.hdj.datepick.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import app.hdj.datepick.ui.utils.rememberUrlImagePainter
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@ExperimentalCoilApi
@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    shimmerShape: Shape = RectangleShape,
    url: Any?,
    imageRequestBuilder: ImageRequest.Builder.() -> Unit = {},
    onFailed: (@Composable () -> Unit)? = null
) {

    val painter = rememberUrlImagePainter(request = url, builder = imageRequestBuilder)
    val state = painter.state

    if (onFailed != null && (state is AsyncImagePainter.State.Error || url == null)) onFailed()
    else {
        val performAnimation = remember(painter.state) { state is AsyncImagePainter.State.Loading }

        Image(
            painter = painter,
            modifier = modifier
                .fillMaxSize()
                .placeholder(
                    visible = performAnimation,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.1f),
                    shape = shimmerShape,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = MaterialTheme.colorScheme.onBackground.copy(
                            0.2f
                        )
                    )
                ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

    }


}