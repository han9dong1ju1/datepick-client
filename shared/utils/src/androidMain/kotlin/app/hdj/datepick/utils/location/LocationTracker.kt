package app.hdj.datepick.utils.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Singleton
@SuppressLint("MissingPermission")
actual class LocationTracker @Inject constructor(val context: Context) {

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    actual suspend fun getCurrentLocation() = kotlin.runCatching {
        fusedLocationProviderClient.lastLocation.await()
            .run { LatLng(latitude, longitude) }
    }.getOrNull()

    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

    private val _locationFlow = MutableStateFlow<LatLng?>(null)

    actual val locationFlow
        get() = _locationFlow
            .filterNotNull()
            .distinctUntilChanged()

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            super.onLocationResult(result)
            coroutineScope.launch {
                _locationFlow.emit(
                    LatLng(
                        result.lastLocation.latitude,
                        result.lastLocation.longitude
                    )
                )
            }
        }
    }

    fun startObserving() {
        fusedLocationProviderClient.requestLocationUpdates(
            LocationRequest.create().setInterval(1000),
            locationCallback,
            Looper.getMainLooper()
        )
    }

    fun stopObserving() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        coroutineScope.cancel()
    }

}