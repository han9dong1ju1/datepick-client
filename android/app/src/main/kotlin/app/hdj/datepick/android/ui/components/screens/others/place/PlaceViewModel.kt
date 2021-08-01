package app.hdj.datepick.android.ui.components.screens.others.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.place.PlaceViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.data.model.place.Place
import app.hdj.datepick.domain.repo.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakePlaceViewModel() = object : PlaceViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State(app.hdj.datepick.domain.StateData.Loading()))

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) = Unit

}

interface PlaceViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val place: app.hdj.datepick.domain.StateData<app.hdj.datepick.data.model.place.Place> = app.hdj.datepick.domain.StateData.Loading(),
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
    private val placeRepository: app.hdj.datepick.domain.repo.PlaceRepository,
) : ViewModel(), PlaceViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    private val placeId = MutableStateFlow<String?>(null)

    private val place = placeId
        .filterNotNull()
        .flatMapConcat { placeRepository.getPlace(it) }

    private val rating = placeId
        .filterNotNull()
        .map { 0.0 }

    private val blogReviews = placeId
        .filterNotNull()
        .flatMapConcat { placeRepository.queryPlaceBlogReviews(it) }

    override val state: StateFlow<State> = combine(
        place,
        rating
    ) { place, rating ->
        State(place = place)
    }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.ReloadContents -> {

                }
                is Event.RequestPlace -> placeId.emit(event.id)
                Event.LikePlace -> {
                    placeId.value?.let {
                        placeRepository.likePlace(it)
                    }
                }
            }

        }
    }

}