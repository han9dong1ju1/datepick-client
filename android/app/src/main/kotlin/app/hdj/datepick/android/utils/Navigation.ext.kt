package app.hdj.datepick.android.utils

import app.hdj.datepick.android.ui.destinations.CourseDetailScreenDestination
import app.hdj.datepick.android.ui.destinations.FeaturedDetailScreenDestination
import app.hdj.datepick.android.ui.destinations.PlaceDetailScreenDestination
import app.hdj.datepick.android.ui.destinations.PlaceListScreenDestination
import app.hdj.datepick.android.ui.destinations.CourseListScreenDestination
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

val DestinationsNavigator.onPlaceClicked get() = { place : Place ->
    navigate(PlaceDetailScreenDestination(place))
}

val DestinationsNavigator.onMorePlaceListClicked get() = { placeQueryParams : PlaceQueryParams ->
    navigate(PlaceListScreenDestination(placeQueryParams))
}

val DestinationsNavigator.onMoreCourseListClicked get() = { courseQueryParams : CourseQueryParams ->
    navigate(CourseListScreenDestination(courseQueryParams))
}

val DestinationsNavigator.onCourseClicked get() = { course : Course ->
    navigate(CourseDetailScreenDestination(course))
}

val DestinationsNavigator.onFeaturedClicked get() = { featured : Featured ->
    navigate(FeaturedDetailScreenDestination(featured))
}