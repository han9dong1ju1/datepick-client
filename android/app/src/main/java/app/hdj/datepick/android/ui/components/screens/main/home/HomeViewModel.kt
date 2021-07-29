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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

fun fakeHomeViewModel() = object : HomeViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(
        State(

        )
    )

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface HomeViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val featuredCourses : StateData<List<FeaturedCourses>> = StateData.Loading()
    )

    sealed class Effect {

    }

    sealed class Event {
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

    private val featuredCourses = courseRepository.getFeaturedCourses()

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.ReloadContents -> {

                }
            }
        }
    }

}