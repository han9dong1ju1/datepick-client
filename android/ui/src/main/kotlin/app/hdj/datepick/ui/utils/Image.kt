package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

@ExperimentalCoilApi
@Composable
fun rememberUrlImagePainter(
    request: Any?,
    imageLoader: ImageLoader = LocalImageLoader.current,
    onExecute: ImagePainter.ExecuteCallback = ImagePainter.ExecuteCallback.Lazy,
    builder: ImageRequest.Builder.() -> Unit = {},
) = rememberImagePainter(
    request,
    imageLoader,
    onExecute,
    builder
)