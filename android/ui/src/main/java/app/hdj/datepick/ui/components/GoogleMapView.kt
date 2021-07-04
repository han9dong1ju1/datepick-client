package app.hdj.datepick.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context)
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle, mapView) {
        val lifecycleObserver = getMapLifecycleObserver(mapView)
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

private fun getMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> mapView.onCreate(bundleOf())
            Lifecycle.Event.ON_START -> mapView.onStart()
            Lifecycle.Event.ON_RESUME -> mapView.onResume()
            Lifecycle.Event.ON_PAUSE -> mapView.onPause()
            Lifecycle.Event.ON_STOP -> mapView.onStop()
            Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
            else -> throw IllegalStateException()
        }
    }

@Composable
fun GoogleMap(latitude: Double, longitude: Double) {

    val map = rememberMapViewWithLifecycle()

    val cameraPosition = remember(latitude, longitude) {
        LatLng(latitude, longitude)
    }

    var mapInitialized by remember(map) { mutableStateOf(false) }
    LaunchedEffect(map, mapInitialized) {
        if (!mapInitialized) {
            val googleMap = map.awaitMap()
            googleMap.uiSettings.isZoomGesturesEnabled = true
            googleMap.uiSettings.setAllGesturesEnabled(true)
            googleMap.addMarker { position(cameraPosition) }
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
            mapInitialized = true
        }
    }

    val coroutineScope = rememberCoroutineScope()
    AndroidView({ map }) { mapView ->
        coroutineScope.launch {
            val googleMap = mapView.awaitMap()
            googleMap.uiSettings.isZoomGesturesEnabled = true
            googleMap.uiSettings.setAllGesturesEnabled(true)
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
        }
    }
}