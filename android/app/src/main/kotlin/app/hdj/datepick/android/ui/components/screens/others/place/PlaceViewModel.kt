package app.hdj.datepick.android.ui.components.screens.others.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.place.PlaceViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakePlaceViewModel() = object : PlaceViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) = Unit

}

interface PlaceViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State()

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
class PlaceViewModel @Inject constructor() : ViewModel(), PlaceViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    private val placeId = MutableStateFlow<String?>(null)

    override val state: StateFlow<State> = TODO()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {

            }

        }
    }

}