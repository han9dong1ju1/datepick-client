package app.hdj.datepick.android.ui.screens.others.createCourse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.android.ui.screens.others.createCourse.CreateCourseViewModelDelegate.*
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.CourseTag
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakeCreateCourseViewModel() = object : CreateCourseViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface CreateCourseViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(
        val availableTags: LoadState<List<CourseTag>> = LoadState.loading(),
        val recommendedPlaces: LoadState<List<Place>> = LoadState.loading(),
        val selectedTags: List<CourseTag> = emptyList(),
        val selectedPlaces: List<Place> = emptyList(),
    )

    sealed class Effect {
    }

    sealed class Event {
        class SelectTag(val tag: CourseTag) : Event()
        class SelectPlace(val place: Place) : Event()
    }

}

@HiltViewModel
class CreateCourseViewModel @Inject constructor(

) : ViewModel(), CreateCourseViewModelDelegate {

    private val availableTags = MutableStateFlow<LoadState<List<CourseTag>>>(LoadState.loading())
    private val recommendedPlaces = MutableStateFlow<LoadState<List<Place>>>(LoadState.loading())
    private val selectedTags = MutableStateFlow<List<CourseTag>>(emptyList())
    private val selectedPlaces = MutableStateFlow<List<Place>>(emptyList())

    override val state = combine(
        availableTags, recommendedPlaces,
        selectedTags, selectedPlaces
    ) { availableTags, recommendedPlaces, selectedTags, selectedPlaces ->
        State(
            availableTags,
            recommendedPlaces,
            selectedTags,
            selectedPlaces
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(2000)

            val list = listOf(
                "크리스마스", "기념일", "생일파티", "액티비티",
                "맛있는 음식", "영화", "여행", "기념일"
            ).map {
                object : CourseTag {
                    override val id: Long = System.currentTimeMillis()
                    override val name: String = it
                }
            }

            availableTags.emit(LoadState.success(list))

            recommendedPlaces.emit(LoadState.success(
                FakePlacePreviewProvider().values.first()
            ))
        }
    }

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.SelectPlace -> {
                    val prevValue = selectedPlaces.value.toMutableList()

                    val newList = if (prevValue.contains(event.place)) {
                        prevValue - event.place
                    } else {
                        prevValue + event.place
                    }

                    selectedPlaces.emit(newList)
                }
                is Event.SelectTag -> {
                    val prevValue = selectedTags.value.toMutableList()

                    val newList = if (prevValue.contains(event.tag)) {
                        prevValue - event.tag
                    } else {
                        prevValue + event.tag
                    }

                    selectedTags.emit(newList)
                }
            }
        }
    }

}