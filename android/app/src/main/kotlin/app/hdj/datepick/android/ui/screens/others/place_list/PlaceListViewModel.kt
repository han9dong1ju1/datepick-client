package app.hdj.datepick.android.ui.screens.others.place_list

import androidx.lifecycle.ViewModel
import app.hdj.datepick.android.ui.screens.others.place_list.PlaceListViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


fun fakePlaceListViewModel() = object : PlaceListViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface PlaceListViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(

    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class PlaceListViewModel @Inject constructor(
) : ViewModel(), PlaceListViewModelDelegate {

    override val state = TODO()

    override val effect: Flow<Effect>
        get() = TODO("Not yet implemented")

    private var job : Job? = null

    override fun event(event: Event) {
        when (event) {

        }
    }

}