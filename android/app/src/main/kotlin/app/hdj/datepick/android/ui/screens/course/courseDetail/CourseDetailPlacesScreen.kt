package app.hdj.datepick.android.ui.screens.course.courseDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DragHandle
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.CoursePlaceListItem
import app.hdj.datepick.android.ui.components.list.CoursePlaceListItemType
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.moved
import app.hdj.datepick.android.utils.rememberMutableStateListOf
import app.hdj.datepick.data.mapper.PlaceMapper.dataToDomain
import app.hdj.datepick.data.utils.MockResponses
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.fold
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.presentation.coursedetail.CourseDetailPlacesScreenViewModel
import app.hdj.datepick.presentation.coursedetail.CourseDetailPlacesScreenViewModelDelegate
import app.hdj.datepick.presentation.coursedetail.CourseDetailScreenViewModel
import app.hdj.datepick.presentation.coursedetail.CourseDetailScreenViewModelDelegate
import app.hdj.datepick.ui.components.BaseScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.burnoutcrew.reorderable.*

@Composable
@Destination(
    navGraph = COURSE_SCREEN_NAV_GRAPH,
)
fun CourseDetailPlacesScreen(
    navigator: DestinationsNavigator,
    parentVm: CourseDetailScreenViewModel
) {
    val vm = hiltViewModel<CourseDetailPlacesScreenViewModel>()

    LaunchedEffect(true) {
        parentVm.state.onEach {
            it.courseState.getDataOrNull()?.id?.let { courseId ->
                vm.event(CourseDetailPlacesScreenViewModelDelegate.Event.SetCourseId(courseId))
            }
        }.launchIn(this)
    }

    CourseDetailPlacesScreenContent(
        vm,
        parentVm,
        onPlaceClicked = {

        }
    )
}

@Composable
private fun CourseDetailPlacesScreenContent(
    vm: CourseDetailPlacesScreenViewModelDelegate,
    parentVm: CourseDetailScreenViewModelDelegate,
    onPlaceClicked: (Place) -> Unit
) {
    val (state) = vm.extract()
    val (parentState) = parentVm.extract()

    val reorderState = rememberReorderState()

    var places by remember {
        mutableStateOf(List(5) { MockResponses.place().dataToDomain() })
    }

//    LaunchedEffect(state.placesState) {
//        val placesState = state.placesState
//        if (placesState.isStateSucceed()) {
//            places = mutableStateListOf()
//            places.addAll(placesState.data)
//        }
//    }

    BaseScaffold {
        LazyColumn(
            state = reorderState.listState,
            modifier = Modifier.padding(it).run {
                if (parentState.inEditMode) {
                    reorderable(reorderState, { from, to ->
                        places = places.moved(from.index, to.index)
                    })
                } else {
                    this
                }
            }
        ) {
            when (state.placesState) {
                is LoadState.Failed -> {

                }
                is LoadState.Success -> {
                    itemsIndexed(places, key = { _, item -> item.id }) { index, place ->
                        Surface(
                            modifier = Modifier.animateItemPlacement()
                                .draggedItem(reorderState.offsetByKey(place.id))
                                .detectReorderAfterLongPress(reorderState),
                            color = MaterialTheme.colors.background
                        ) {
                            CoursePlaceListItem(
                                modifier = Modifier.fillMaxWidth(),
                                dragHandle = {
                                    Icon(
                                        modifier = Modifier.detectReorder(reorderState),
                                        imageVector = Icons.Rounded.DragHandle,
                                        contentDescription = null
                                    )
                                },
                                place,
                                if (index == 0) CoursePlaceListItemType.TOP
                                else {
                                    if (index == places.lastIndex) CoursePlaceListItemType.BOTTOM
                                    else CoursePlaceListItemType.MIDDLE
                                },
                                parentState.inEditMode,
                                onPlaceClicked
                            )
                        }
                    }

                    item {

                    }
                }
                else -> {
                    item {
                        Box(modifier = Modifier.fillMaxWidth().padding(20.dp).animateItemPlacement()) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp).align(Alignment.Center).animateItemPlacement()
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }
}