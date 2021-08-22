package app.hdj.datepick.android.ui.screens.others.placeList

import androidx.lifecycle.ViewModel
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.android.ui.screens.others.placeList.PlaceListViewModelDelegate.*
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
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

    override val state = MutableStateFlow(
        State(LoadState.success(FakePlacePreviewProvider().values.first()))
    )

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface PlaceListViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val results: LoadState<List<Place>> = LoadState.loading()
    )

    sealed class Effect {

    }

    sealed class Event {
        data class Search(val query: String) : Event()
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

    private var job: Job? = null

    override fun event(event: Event) {
        when (event) {

        }
    }

}