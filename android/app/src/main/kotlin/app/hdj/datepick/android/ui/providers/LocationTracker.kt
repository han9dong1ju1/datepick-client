package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.staticCompositionLocalOf
import app.hdj.datepick.utils.location.LocationTracker

val LocalLocationTracker = staticCompositionLocalOf<LocationTracker> { error("") }