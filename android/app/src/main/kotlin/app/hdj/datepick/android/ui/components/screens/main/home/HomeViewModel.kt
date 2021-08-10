package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.main.home.HomeViewModelDelegate.*
import app.hdj.datepick.android.ui.providers.preview.FakeFeaturedPreviewProvider
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.usecase.featured.GetFeaturedListUseCase
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

fun fakeHomeViewModel() = object : HomeViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(
        State(StateData.success(FakeFeaturedPreviewProvider().values.first()))
    )

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface HomeViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val featured: StateData<List<Featured>> = StateData.loading()
    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class HomeViewModel @Inject constructor(
    getFeaturedListUseCase: GetFeaturedListUseCase
) : ViewModel(), HomeViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    override val state: StateFlow<State> = combine(
        getFeaturedListUseCase.execute(),
        flowOf(true)
    ) { featured, _ ->
        State(featured)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        State()
    )

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.ReloadContents -> {

                }
            }
        }
    }

}