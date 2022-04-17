package app.hdj.datepick.utils.location

import kotlinx.coroutines.flow.Flow

expect class LocationTracker {

    suspend fun getCurrentLocation(): LatLng?

    val locationFlow : Flow<LatLng>

    fun startObserving()

    fun stopObserving()

}