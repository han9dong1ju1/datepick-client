package app.hdj.datepick.ui.components

import android.view.View
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.*
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.model.cameraPosition
import com.google.maps.android.ktx.model.polylineOptions

private val SEOUL_LAT_LNG = LatLng(
    37.5665,
    126.9780
)

typealias CameraUpdateWrapper = () -> CameraUpdate

@Stable
class CameraUpdateState {

    private var _cameraUpdate: Pair<CameraUpdateWrapper, Boolean>? by mutableStateOf(null)
    val cameraUpdate get() = _cameraUpdate

    fun moveCamera(latLng: LatLng, zoomLevel: Float) {
        _cameraUpdate = {
            CameraUpdateFactory.newCameraPosition(
                cameraPosition {
                    target(latLng)
                    zoom(zoomLevel)
                }
            )
        } to false
    }

    fun moveCamera(positions: List<LatLng>, padding: Dp) {
        _cameraUpdate = {
            CameraUpdateFactory.newLatLngBounds(
                LatLngBounds.builder()
                    .apply { positions.forEach(this::include) }
                    .build(),
                padding.value.toInt()
            )
        } to false
    }

    fun animateCamera(latLng: LatLng, zoomLevel: Float) {
        _cameraUpdate = {
            CameraUpdateFactory.newCameraPosition(
                cameraPosition {
                    target(latLng)
                    zoom(zoomLevel)
                }
            )
        } to true
    }

    fun animateCamera(positions: List<LatLng>, padding: Dp) {
        _cameraUpdate = {
            CameraUpdateFactory.newLatLngBounds(
                LatLngBounds.builder()
                    .apply { positions.forEach(this::include) }
                    .build(),
                padding.value.toInt()
            )
        } to true
    }

}

@Composable
fun rememberGoogleMapCameraPosition(): CameraUpdateState = remember {
    CameraUpdateState()
}

@Stable
class MarkerOptionsState {

    private var _markerOptions: MutableList<MarkerOptions> by mutableStateOf(mutableListOf())
    val markerOptions: List<MarkerOptions> get() = _markerOptions

    fun clear() {
        _markerOptions = mutableListOf()
    }

    fun addMarkerOptions(
        markerOptions: List<MarkerOptions>
    ) {
        _markerOptions += markerOptions
    }

}


@Composable
fun rememberGoogleMapMarkers() = remember { MarkerOptionsState() }

@Stable
class PolylineOptionsState {

    private var _polylineOptions: PolylineOptions? by mutableStateOf(null)
    val polylineOptions get() = _polylineOptions

    fun setPolylineOptions(optionsActions: PolylineOptions.() -> Unit) {
        _polylineOptions = polylineOptions(optionsActions)
    }
}

@Composable
fun rememberGoogleMapPolylineOptions() = remember { PolylineOptionsState() }

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

@Composable
fun GoogleMap(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraUpdateState = rememberGoogleMapCameraPosition(),
    markers: MarkerOptionsState = rememberGoogleMapMarkers(),
    polyline: PolylineOptionsState = rememberGoogleMapPolylineOptions()
) {

    val mapView = rememberMapViewWithLifecycle()

    LaunchedEffect(cameraPositionState.cameraUpdate) {
        val googleMap = mapView.awaitMap()
        cameraPositionState.cameraUpdate?.let { (cameraUpdate, animate) ->
            if (animate) googleMap.animateCamera(cameraUpdate())
            else googleMap.moveCamera(cameraUpdate())
        }
    }

    LaunchedEffect(
        markers.markerOptions,
        polyline.polylineOptions
    ) {
        val googleMap = mapView.awaitMap()
        googleMap.clear()
        markers.markerOptions.forEach {
            googleMap.addMarker(it)?.showInfoWindow()
        }
        polyline.polylineOptions?.let { googleMap.addPolyline(it) }
    }

    val context = LocalContext.current

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    AndroidView(
        factory = { mapView },
        modifier = modifier
    ) { view ->
        view.getMapAsync {
            it.moveCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition {
                    target(SEOUL_LAT_LNG)
                    zoom(10f)
                })
            )

//            it.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
//                override fun getInfoWindow(p0: Marker): View? {
//                    return null
//                }
//
//                override fun getInfoContents(p0: Marker) = ComposeView(context).apply {
//                    setViewCompositionStrategy(
//                        ViewCompositionStrategy.DisposeOnLifecycleDestroyed(lifecycle)
//                    )
//                    setContent {
//                        DatePickGoogleMapMarker(marker = p0)
//                    }
//                }
//            })

        }

    }
}

@Composable
fun DatePickGoogleMapMarker(marker: Marker) {

    Surface {
        Text(
            modifier = Modifier.padding(8.dp),
            text = marker.title.orEmpty(),
            style = MaterialTheme.typography.body2
        )
    }

}