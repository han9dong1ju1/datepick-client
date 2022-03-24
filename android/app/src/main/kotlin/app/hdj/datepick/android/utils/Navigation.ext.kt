package app.hdj.datepick.android.utils

import app.hdj.datepick.android.ui.destinations.CourseDetailScreenDestination
import app.hdj.datepick.android.ui.destinations.FeaturedDetailScreenDestination
import app.hdj.datepick.android.ui.destinations.PlaceScreenDestination
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.place.Place
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

val DestinationsNavigator.onPlaceClicked get() = { place : Place ->
    navigate(PlaceScreenDestination(place))
}

val DestinationsNavigator.onCourseClicked get() = { course : Course ->
    navigate(CourseDetailScreenDestination(course))
}

val DestinationsNavigator.onFeaturedClicked get() = { featured : Featured ->
    navigate(FeaturedDetailScreenDestination(featured))
}