@file:Suppress("UnnecessaryVariable")

package app.hdj.datepick.ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.UiSettings
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch

private val SEOUL_LAT_LNG_POSITION = LatLng(
    37.5665,
    126.9780
)

class GoogleMapState {

    var cameraPosition: LatLng = SEOUL_LAT_LNG_POSITION
        private set

    val markers: MutableList<MarkerOptions> = mutableListOf()

    fun moveCameraTo(latLng: LatLng) {
        cameraPosition = latLng
    }

    fun addMarker(markerOptions: MarkerOptions) {
        markers += markerOptions
    }

    fun removeMarkerAt(position: LatLng) {
        markers.removeIf { it.position == position }
    }

}

@Composable
fun rememberGoogleMapState() = remember { GoogleMapState() }

@Composable
private fun rememberMapViewWithLifecycle(): MapView {
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
fun DatePickGoogleMap(
    modifier: Modifier = Modifier,
    googleMapState: GoogleMapState = rememberGoogleMapState(),
    uiSettings: UiSettings.() -> Unit = {
        isZoomGesturesEnabled = true
        setAllGesturesEnabled(true)
    },
    onMarkerClicked: (Marker) -> Unit = {},
) {

    val map = rememberMapViewWithLifecycle()

    var mapInitialized by remember(map) { mutableStateOf(false) }

    var lastGoogleMapState by remember { mutableStateOf(googleMapState) }

    LaunchedEffect(map, mapInitialized) {
        if (!mapInitialized) {
            val googleMap = map.awaitMap()
            googleMap.uiSettings.uiSettings()
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(googleMapState.cameraPosition))
            googleMap.setOnMarkerClickListener {
                onMarkerClicked(it)
                true
            }
            googleMapState.markers.forEach(googleMap::addMarker)
            mapInitialized = true
        }
    }

    val coroutineScope = rememberCoroutineScope()

    AndroidView(
        modifier = modifier,
        factory = { map },
        update = { mapView ->
            val state = googleMapState
            coroutineScope.launch {

                val googleMap = mapView.awaitMap()

                if (lastGoogleMapState.cameraPosition != state.cameraPosition) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(state.cameraPosition))
                    lastGoogleMapState = state
                }

                if (lastGoogleMapState.markers != state.markers) {
                    googleMap.clear()
                    state.markers.forEach(googleMap::addMarker)
                    state.markers.lastOrNull()?.position?.let {
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(it))
                    }
                    if (lastGoogleMapState != state) lastGoogleMapState = state
                }

            }
        }
    )

}