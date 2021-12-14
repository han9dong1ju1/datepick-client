package app.hdj.datepick.android.ui.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.screens.main.home.HomeViewModelDelegate.*
import app.hdj.datepick.android.ui.providers.preview.FakeFeaturedPreviewProvider
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.featured.GetFeaturedListUseCase
import app.hdj.datepick.domain.usecase.invoke
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

fun fakeHomeViewModel() = object : HomeViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(
        State(
            LoadState.success(FakeFeaturedPreviewProvider().values.first()),
            LoadState.success(FakePlacePreviewProvider().values.first()),
        )
    )

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface HomeViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val featured: LoadState<List<Featured>> = LoadState.loading(),
        val featuredPlaces: LoadState<List<Place>> = LoadState.loading(),
    ) {

        val isContentLoading
            get() =
                featured.isStateLoading() ||
                        featuredPlaces.isStateLoading()

    }

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedListUseCase: GetFeaturedListUseCase
) : ViewModel(), HomeViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    private val featuredList = MutableStateFlow<LoadState<List<Featured>>>(LoadState.loading())
    private val featuredPlaces = MutableStateFlow<LoadState<List<Place>>>(LoadState.loading())

    override val state: StateFlow<State> = combine(
        featuredList,
        featuredPlaces
    ) { featured, featuredPlaces ->
        State(
            featured,
            featuredPlaces
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        State()
    )

    init {
        viewModelScope.launch {
            loadContents()
        }
    }

    private suspend fun loadContents() {
        getFeaturedListUseCase().onEach(featuredList::emit).collect()
        featuredPlaces.emit(LoadState.failed(Exception("Not Ready")))
    }

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.ReloadContents -> loadContents()
            }
        }
    }

}