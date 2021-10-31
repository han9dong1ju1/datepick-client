package app.hdj.datepick.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import app.hdj.datepick.ui.utils.rememberUrlImagePainter
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.decode.DataSource
import coil.request.ImageRequest

@ExperimentalCoilApi
@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String?,
    imageRequestBuilder: ImageRequest.Builder.() -> Unit = {},
    onLoading: @Composable () -> Unit = { Shimmer(Modifier.fillMaxSize()) },
    onFailed: @Composable () -> Unit = onLoading,
) {
    Box(modifier) {

        val painter = rememberUrlImagePainter(request = url, builder = imageRequestBuilder)

        val state = painter.state

        val performAnimation =
            !(state is ImagePainter.State.Success && state.result.dataSource != DataSource.MEMORY_CACHE)

        Image(
            painter = painter,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        AnimatedVisibility(
            url == null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            onLoading()
        }

        AnimatedVisibility(
            visible = performAnimation,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            when (state) {
                is ImagePainter.State.Loading -> onLoading()
                is ImagePainter.State.Error -> onFailed()
                else -> {
                }
            }
        }

    }
}