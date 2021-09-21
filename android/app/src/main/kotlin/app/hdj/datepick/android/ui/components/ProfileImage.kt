package app.hdj.datepick.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import app.hdj.datepick.ui.utils.rememberUrlImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    profileImageUrl: String?
) {
    val imagePainter = rememberUrlImagePainter(request = profileImageUrl) {
        transformations(CircleCropTransformation())
    }

    Image(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        painter = imagePainter,
        contentDescription = "Profile Image"
    )
}