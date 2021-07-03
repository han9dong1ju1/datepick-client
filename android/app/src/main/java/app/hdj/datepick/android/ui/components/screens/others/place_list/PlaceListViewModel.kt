package app.hdj.datepick.android.ui.components.screens.others.place_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.place_list.PlaceListViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.data.paging.PlatformPagingData
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.entity.Place
import app.hdj.shared.client.domain.repo.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakePlaceListViewModel() = object : PlaceListViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface PlaceListViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val places: Flow<PlatformPagingData<Place>> = flow {  }
    )

    sealed class Effect {

    }

    sealed class Event {
        data class Query(val query: PlaceQuery) : Event()

        object ReloadContents : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class PlaceListViewModel @Inject constructor(
    private val placeRepository: PlaceRepository
) : ViewModel(), PlaceListViewModelDelegate {

    private val placeQuery = MutableStateFlow(PlaceQuery())

    override val state = placeQuery
        .map { State(placeRepository.queryPlace(it)) }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            State()
        )

    override val effect: Flow<Effect>
        get() = TODO("Not yet implemented")

    private var job : Job? = null

    override fun event(event: Event) {
        when (event) {
            is Event.Query -> {
                job?.cancel()
                job = viewModelScope.launch {
                    delay(500)
                    placeQuery.emit(event.query)
                }
            }
            Event.ReloadContents -> {

            }
        }
    }

}