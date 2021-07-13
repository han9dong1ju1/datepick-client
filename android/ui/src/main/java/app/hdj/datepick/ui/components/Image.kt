package app.hdj.datepick.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.google.accompanist.imageloading.ImageLoadState
import com.google.accompanist.imageloading.LoadPainter

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    loadPainter: LoadPainter<Any>,
    onLoading: @Composable () -> Unit = {},
    onFailed: @Composable () -> Unit = {},
) {
    Box(modifier) {

        Image(
            painter = loadPainter,
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
        )

        when (loadPainter.loadState) {
            ImageLoadState.Empty -> {
            }
            is ImageLoadState.Loading -> onLoading()
            is ImageLoadState.Error -> onFailed()
        }

    }
}