package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.main.home.HomeViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.*
import app.hdj.shared.client.domain.repo.CourseRepository
import app.hdj.shared.client.domain.repo.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

fun fakeHomeViewModel() = object : HomeViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(
        State(
            StateData.Success(listOf("분위기", "사회적거리두기", "가성비", "사람적은", "넓은")),
            StateData.Success(fakeCourseList),
            StateData.Success(fakePlaceList),
            StateData.Success(listOf("인기", "음식", "카페", "공원")),
        )
    )

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface HomeViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val tags: StateData<List<String>> = StateData.Loading(),
        val popularCourses: StateData<List<Course>> = StateData.Loading(),
        val popularPlaces: StateData<List<Place>> = StateData.Loading(),
        val categories: StateData<List<String>> = StateData.Loading(),
        val noticeEvents: StateData<List<NoticeEvent>> = StateData.Loading(),
        val recommendedPlacesFlow: Flow<StateData<List<RecommendedPlaces>>> = flow {}
    )

    sealed class Effect {

    }

    sealed class Event {
        data class SelectCategoryTab(val category: String) : Event()
        object ReloadContents : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class HomeViewModel @Inject constructor(
    courseRepository: CourseRepository,
    placeRepository: PlaceRepository
) : ViewModel(), HomeViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    private val popularCoursesState = flow {
        emit(StateData.Loading())
        delay(5000)
        emit(StateData.Success(fakeCourseList))
    }

    private val popularPlacesState = flow {
        emit(StateData.Loading())
        delay(2000)
        emit(StateData.Success(fakePlaceList))
    }

    private val noticeEventsState = flow {
        emit(StateData.Loading())
        delay(2000)
        emit(StateData.Success(fakeNoticeEvents))
    }

    private val categoriesState = flow {
        emit(StateData.Loading())
        delay(1000)
        emit(StateData.Success(listOf("인기", "음식", "카페", "공원", "공방", "놀이공원", "영화관", "보드게임")))
    }.onEach { categories ->
        if (categories is StateData.Success) {
            categories.data.firstOrNull()?.let { selectedCategory.emit(it) }
        }
    }

    private val tagsState = flow {
        emit(StateData.Loading())
        delay(200)
        emit(StateData.Success(listOf("분위기", "사회적거리두기", "가성비", "사람적은", "넓은")))
    }

    private val selectedCategory = MutableStateFlow<String?>(null)

    private val selectedRecommendedPlaces: Flow<StateData<List<RecommendedPlaces>>> =
        selectedCategory.flatMapConcat { category ->
            flow {
                if (category != null) {
                    delay(200)
                    emit(
                        StateData.Success(
                            listOf(
                                RecommendedPlaces(
                                    "가성비 넘치는 곳 \uD83D\uDCB0",
                                    fakePlaceList.shuffled()
                                ),
                                RecommendedPlaces(
                                    "분위기 좋은 곳 \uD83D\uDECB",
                                    fakePlaceList.shuffled()
                                ),
                                RecommendedPlaces(
                                    "거리두기 잘 하는 곳\uD83D\uDE37",
                                    fakePlaceList.shuffled()
                                ),
                                RecommendedPlaces(
                                    "서비스가 친절한 곳 \uD83D\uDE0A",
                                    fakePlaceList.shuffled()
                                ),
                            )
                        )
                    )
                } else emit(StateData.Loading<List<RecommendedPlaces>>())
            }
        }

    override val state =
        combine(
            tagsState,
            popularCoursesState,
            popularPlacesState,
            noticeEventsState,
            categoriesState
        ) { tags, courses, places, noticeEvents, categories ->
            State(
                tags = tags,
                popularCourses = courses,
                popularPlaces = places,
                categories = categories,
                noticeEvents = noticeEvents,
                recommendedPlacesFlow = selectedRecommendedPlaces
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            State()
        )

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.ReloadContents -> {

                }
                is Event.SelectCategoryTab -> {
                    selectedCategory.emit(event.category)
                }
            }
        }
    }

}