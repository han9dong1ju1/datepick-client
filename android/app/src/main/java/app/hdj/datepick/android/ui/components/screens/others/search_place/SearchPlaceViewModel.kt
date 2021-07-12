package app.hdj.datepick.android.ui.components.screens.others.search_place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.search_place.SearchPlaceViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.utils.DatePickGooglePlacesClient
import app.hdj.shared.client.utils.Origin
import app.hdj.shared.client.utils.PlacesAutocompletePredictionsQuery
import app.hdj.shared.client.utils.PlacesAutocompletePredictionsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakeSearchPlaceViewModel() = object : SearchPlaceViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface SearchPlaceViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(
        val searchResult: StateData<List<PlacesAutocompletePredictionsResponse>>? = null
    )

    sealed class Effect {

    }

    sealed class Event {
        class Search(val search: String, val lat: Double, val lon: Double) : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class SearchPlaceViewModel @Inject constructor(
    private val placesClient: DatePickGooglePlacesClient
) : ViewModel(), SearchPlaceViewModelDelegate {

    private val searchQuery = MutableStateFlow<PlacesAutocompletePredictionsQuery?>(null)

    private val searchResult =
        searchQuery.filterNotNull().flatMapConcat {
            flow {
                emit(StateData.Loading())

                placesClient.runCatching {
                    findAutocompletePredictionsFromQuery(it)
                }.onSuccess {
                    if (it.isEmpty()) emit(StateData.Failed(it))
                    else emit(StateData.Success(it))
                }.onFailure {
                    emit(
                        StateData.Failed(
                            cachedData = emptyList<PlacesAutocompletePredictionsResponse>(),
                            throwable = it
                        )
                    )
                }

            }
        }

    override val state = searchResult.map {
        State(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        State()
    )

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    private var job: Job? = null

    override fun event(event: Event) {
        when (event) {
            is Event.Search -> {
                job?.cancel()
                job = viewModelScope.launch {
                    delay(1000)
                    searchQuery.emit(
                        PlacesAutocompletePredictionsQuery(
                            event.search,
                            Origin(event.lat, event.lon)
                        )
                    )
                }
            }
        }
    }

}