package app.hdj.datepick.android.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.components.Shimmer
import coil.transform.CircleCropTransformation

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    profileImageUrl: String?
) {

    NetworkImage(
        modifier = modifier,
        url = profileImageUrl,
        imageRequestBuilder =  {
            transformations(CircleCropTransformation())
        },
        onLoading = {
            Surface(modifier, shape = CircleShape) {
                Shimmer(Modifier.fillMaxSize())
            }
        }
    )
}