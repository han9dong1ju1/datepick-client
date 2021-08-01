package app.hdj.datepick.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter

@ExperimentalCoilApi
@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imagePainter: ImagePainter,
    onLoading: @Composable () -> Unit = {},
    onFailed: @Composable () -> Unit = {},
) {
    Box(modifier) {

        Image(
            painter = imagePainter,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        when (imagePainter.state) {
            is ImagePainter.State.Loading -> onLoading()
            is ImagePainter.State.Error -> onFailed()
            else -> {}
        }

    }
}