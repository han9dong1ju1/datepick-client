package app.hdj.datepick.ui.screens.others.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.screens.others.place.PlaceViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Place
import app.hdj.shared.client.domain.repo.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakePlaceViewModel() = object : PlaceViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State(StateData.Loading()))

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) = Unit

}

interface PlaceViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val place: StateData<Place> = StateData.Loading(),
    )

    sealed class Effect {
        class ShowToastMessage(val message: String) : Effect()
    }

    sealed class Event {
        object ReloadContents : Event()
        class RequestPlace(val id: String) : Event()
        object LikePlace : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class PlaceViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
) : ViewModel(), PlaceViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    private val placeId = MutableStateFlow<String?>(null)

    private val place = placeId.filterNotNull().flatMapConcat { placeRepository.getPlace(it) }

    private val rating = placeId.filterNotNull().map { 0.0 }

    override val state: StateFlow<State> = combine(
        place,
        rating
    ) { place, rating ->
        State(place = place)
    }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.ReloadContents -> {

                }
                is Event.RequestPlace -> placeId.value = event.id
                Event.LikePlace -> {
                    placeId.value?.let {
                        placeRepository.likePlace(it)
                    }
                }
            }

        }
    }

}