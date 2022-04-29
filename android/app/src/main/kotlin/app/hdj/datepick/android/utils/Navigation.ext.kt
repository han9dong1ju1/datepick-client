package app.hdj.datepick.android.utils

import androidx.compose.runtime.Composable
import app.hdj.datepick.android.ui.destinations.*
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

val DestinationsNavigator.onPlaceClicked : (Place) -> Unit @Composable get() = { place : Place ->
    navigate(PlaceDetailScreenDestination(place))
}

val DestinationsNavigator.onMorePlaceListClicked : (PlaceQueryParams) -> Unit @Composable get() = { placeQueryParams : PlaceQueryParams ->
    navigate(PlaceListScreenDestination(placeQueryParams))
}

val DestinationsNavigator.onMoreCourseListClicked  : (CourseQueryParams) -> Unit @Composable get() = { courseQueryParams : CourseQueryParams ->
    navigate(CourseListScreenDestination(courseQueryParams))
}

val DestinationsNavigator.onCourseClicked : (Course) -> Unit @Composable get() = { course : Course ->
    navigate(CourseDetailScreenDestination(course))
}

val DestinationsNavigator.onFeaturedClicked  : (Featured) -> Unit @Composable get() = { featured : Featured ->
    navigate(FeaturedDetailScreenDestination(featured))
}

val DestinationsNavigator.onMoreFeaturedListClicked : () -> Unit @Composable get() = {
    navigate(FeaturedListScreenDestination)
}