package app.hdj.datepick.utils.location

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class LocationTracker {

    actual suspend fun getCurrentLocation(): LatLng? {
        return null
    }

    actual val locationFlow: Flow<LatLng> = flowOf(LatLng(0.0, 0.0))

}