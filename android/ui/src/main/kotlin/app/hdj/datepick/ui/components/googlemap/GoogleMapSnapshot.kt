package app.hdj.datepick.ui.components.googlemap

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GoogleMapSnapshot(location: LatLng, zoom : Float = 15f) {

    val map = rememberMapViewWithLifecycle()

    var mapBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val coroutineScope = rememberCoroutineScope()

    if (mapBitmap != null) {
        Image(
            bitmap = mapBitmap!!.asImageBitmap(),
            contentDescription = "Map snapshot",
        )
    } else {
        AndroidView({ map }) { mapView ->
            coroutineScope.launch {
                val googleMap = mapView.awaitMap()
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoom))
                googleMap.addMarker { position(location) }
                googleMap.setOnMapLoadedCallback {
                    googleMap.snapshot { mapBitmap = it }
                }
            }
        }
    }

    DisposableEffect(location) {
        mapBitmap = null
        onDispose { mapBitmap = null }
    }
}