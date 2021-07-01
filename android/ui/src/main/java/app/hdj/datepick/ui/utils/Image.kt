package app.hdj.datepick.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize
import app.hdj.datepick.ui.R
import coil.ImageLoader
import coil.request.ImageRequest
import com.google.accompanist.coil.CoilPainterDefaults
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.LoadPainterDefaults
import com.google.accompanist.imageloading.ShouldRefetchOnSizeChange

@Composable
fun rememberUrlImagePainter(
    request: String?,
    imageLoader: ImageLoader = CoilPainterDefaults.defaultImageLoader(),
    shouldRefetchOnSizeChange: ShouldRefetchOnSizeChange = ShouldRefetchOnSizeChange { _, _ -> false },
    requestBuilder: (ImageRequest.Builder.(size: IntSize) -> ImageRequest.Builder)? = null,
    fadeIn: Boolean = true,
    fadeInDurationMs: Int = LoadPainterDefaults.FadeInTransitionDuration,
    @DrawableRes previewPlaceholder: Int = R.drawable.img_cat,
) = rememberCoilPainter(
    request,
    imageLoader,
    shouldRefetchOnSizeChange,
    requestBuilder,
    fadeIn,
    fadeInDurationMs,
    previewPlaceholder
)