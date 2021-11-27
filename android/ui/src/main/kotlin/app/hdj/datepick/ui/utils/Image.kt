package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

@ExperimentalCoilApi
@Composable
fun rememberUrlImagePainter(
    request: Any?,
    imageLoader: ImageLoader = LocalImageLoader.current,
    builder: ImageRequest.Builder.() -> Unit = {},
) = rememberAsyncImagePainter(
    ImageRequest.Builder(LocalContext.current).data(request).apply(
        builder
    ).build(),
    imageLoader
)