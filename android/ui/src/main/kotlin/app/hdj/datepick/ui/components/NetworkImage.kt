package app.hdj.datepick.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import app.hdj.datepick.ui.utils.rememberUrlImagePainter
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest

@ExperimentalCoilApi
@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: Any?,
    imageRequestBuilder: ImageRequest.Builder.() -> Unit = {},
    onFailed: (@Composable () -> Unit)? = null
) {

    val painter = rememberUrlImagePainter(request = url, builder = imageRequestBuilder)

    if (onFailed != null && (painter.state is AsyncImagePainter.State.Error || url == null)) onFailed()
    else {
        Image(
            painter = painter,
            modifier = modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

    }


}