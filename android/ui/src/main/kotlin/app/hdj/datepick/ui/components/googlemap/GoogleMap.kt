package app.hdj.datepick.ui.components.googlemap

import android.graphics.Color
import android.view.View
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.maps.android.collections.MarkerManager
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.model.cameraPosition
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.BitmapDescriptor
import timber.log.Timber


private val SEOUL_LAT_LNG = LatLng(
    37.5665,
    126.9780
)

typealias CameraUpdateWrapper = () -> CameraUpdate

sealed interface CameraUpdateRequest {

    val animate: Boolean

    data class MultipleTargets(
        val targets: List<LatLng>, val padding: Dp,
        override val animate: Boolean
    ) : CameraUpdateRequest

    data class SpecificTarget(
        val target: LatLng, val zoom: Float,
        override val animate: Boolean
    ) : CameraUpdateRequest

}

@Stable
class CameraUpdateState {

    private var _cameraUpdateRequest: CameraUpdateRequest? by mutableStateOf(null)
    val cameraUpdateRequest get() = _cameraUpdateRequest

    fun move(targets: List<LatLng>, padding: Dp) {
        request(targets, padding, false)
    }


    fun move(target: LatLng, zoom: Float) {
        request(target, zoom, false)
    }

    fun animate(targets: List<LatLng>, padding: Dp) {
        request(targets, padding, true)
    }


    fun animate(target: LatLng, zoom: Float) {
        request(target, zoom, true)
    }

    private fun request(targets: List<LatLng>, padding: Dp, animate: Boolean) {
        _cameraUpdateRequest = CameraUpdateRequest.MultipleTargets(targets, padding, animate)
    }

    private fun request(target: LatLng, zoom: Float, animate: Boolean) {
        _cameraUpdateRequest = CameraUpdateRequest.SpecificTarget(target, zoom, animate)
    }

}

@Composable
fun rememberCameraUpdateState(
    initialTarget: LatLng = SEOUL_LAT_LNG,
    initialZoom: Float = 10f
): CameraUpdateState = remember {
    CameraUpdateState().apply {
        move(initialTarget, initialZoom)
    }
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
fun rememberMarkerOptionsState() = remember { MarkerOptionsState() }

@Stable
class PolylineOptionsState {

    private var _polylineOptions: PolylineOptions? by mutableStateOf(null)
    val polylineOptions get() = _polylineOptions

    fun polylineOptions(optionsActions: PolylineOptions.() -> Unit) {
        _polylineOptions = com.google.maps.android.ktx.model.polylineOptions(optionsActions)
    }
}

@Composable
fun rememberPolylineOptionsState() = remember { PolylineOptionsState() }

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

@Stable
class MapUiSettingsState {

    private var _uiSettings: (UiSettings.() -> Unit)? by mutableStateOf(null)
    val uiSettings get() = _uiSettings

    fun uiSettings(optionsActions: UiSettings.() -> Unit) {
        _uiSettings = optionsActions
    }

}

@Composable
fun rememberMapUiSettings(
    optionsActions: UiSettings.() -> Unit = {
        setAllGesturesEnabled(true)
    }
): MapUiSettingsState = remember {
    MapUiSettingsState().apply {
        uiSettings(optionsActions)
    }
}

@Composable
fun GoogleMap(
    modifier: Modifier = Modifier,
    uiSettingsState: MapUiSettingsState = rememberMapUiSettings(),
    cameraUpdateState: CameraUpdateState = rememberCameraUpdateState(),
    markerOptionsState: MarkerOptionsState = rememberMarkerOptionsState(),
    polylineOptionsState: PolylineOptionsState = rememberPolylineOptionsState()
) {

    val mapView = rememberMapViewWithLifecycle()

    LaunchedEffect(cameraUpdateState.cameraUpdateRequest) {
        val googleMap = mapView.awaitMap()
        cameraUpdateState.cameraUpdateRequest?.let {
            when (it) {
                is CameraUpdateRequest.MultipleTargets -> {
                    val update = CameraUpdateFactory.newLatLngBounds(
                        LatLngBounds.builder()
                            .apply { it.targets.forEach(this::include) }
                            .build(),
                        it.padding.value.toInt()
                    )
                    if (it.animate) googleMap.animateCamera(update)
                    else googleMap.moveCamera(update)
                }
                is CameraUpdateRequest.SpecificTarget -> {
                    val update = CameraUpdateFactory.newCameraPosition(
                        cameraPosition {
                            target(it.target)
                            zoom(it.zoom)
                        }
                    )
                    if (it.animate) googleMap.animateCamera(update)
                    else googleMap.moveCamera(update)
                }
            }
        }
    }

    LaunchedEffect(
        markerOptionsState.markerOptions,
        polylineOptionsState.polylineOptions
    ) {
        val googleMap = mapView.awaitMap()
        googleMap.clear()
        markerOptionsState.markerOptions.forEach {
            googleMap.addMarker(it)?.showInfoWindow()
        }
        polylineOptionsState.polylineOptions?.let { googleMap.addPolyline(it) }
    }

    AndroidView(
        factory = { mapView },
        modifier = modifier
    ) { view ->
        view.getMapAsync {
            uiSettingsState.uiSettings?.invoke(it.uiSettings)
            val markerManager = MarkerManager(it)
            val collection = markerManager.newCollection()
            collection.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
                override fun getInfoWindow(p0: Marker): View? {
                    return null
                }

                override fun getInfoContents(p0: Marker): View? {
                    return null
                }
            })
        }
    }
}

fun androidx.compose.ui.graphics.Color.getMarkerIcon(): BitmapDescriptor? {
    val hsv = FloatArray(3)
    Color.colorToHSV(toArgb(), hsv)
    return BitmapDescriptorFactory.defaultMarker(hsv[0])
}