package app.hdj.datepick.presentation.kakaoplacesearch

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.KakaoPlaceSearch
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.domain.usecase.place.GetKakaoPlaceSearchUseCase
import app.hdj.datepick.domain.usecase.place.params.KakaoPlaceSearchQueryParams
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.kakaoplacesearch.KakaoPlaceSearchScreenViewModelDelegate.*
import app.hdj.datepick.presentation.utils.toLoadState
import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.utils.PlatformLogger
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.location.LocationTracker
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface KakaoPlaceSearchScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val results: LoadState<List<KakaoPlaceSearch.Document>>? = null,
    )

    sealed interface Effect {
        object PlaceAdded : Effect
    }

    sealed interface Event {
        data class Search(val query: String) : Event
        data class Select(val place: KakaoPlaceSearch.Document) : Event
    }

}

@HiltViewModel
class KakaoPlaceSearchScreenViewModel @Inject constructor(
    private val getKakaoPlaceSearchUseCase: GetKakaoPlaceSearchUseCase,
    private val locationTracker: LocationTracker
) : PlatformViewModel(), KakaoPlaceSearchScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val kakaoPlaceSearchResult = MutableStateFlow<LoadState<List<KakaoPlaceSearch.Document>>?>(null)

    override val state: StateFlow<State> = kakaoPlaceSearchResult.map {
        State(results = it)
    }.asStateFlow(State(), platformViewModelScope)

    init {
        locationTracker.startObserving()
    }

    override fun onViewModelCleared() {
        super.onViewModelCleared()
        locationTracker.stopObserving()
    }

    override fun event(e: Event) {
        when (e) {
            is Event.Search -> {
                platformViewModelScope.launch {
                    val location = locationTracker.getCurrentLocation()

                    val params = KakaoPlaceSearchQueryParams(
                        query = e.query,
                        latLng = location
                    )

                    getKakaoPlaceSearchUseCase(params)
                        .toLoadState()
                        .onEach { kakaoPlaceSearchResult.emit(it)  }
                        .launchIn(this)
                }
            }
            is Event.Select -> {
                platformViewModelScope.launch {

                }
            }
        }
    }

}