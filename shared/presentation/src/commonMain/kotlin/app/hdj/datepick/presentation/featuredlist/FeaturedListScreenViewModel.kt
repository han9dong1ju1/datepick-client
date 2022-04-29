package app.hdj.datepick.presentation.featuredlist

import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.usecase.featured.GetFeaturedListUseCase
import app.hdj.datepick.domain.usecase.featured.GetTopFeaturedListUseCase
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.featuredlist.FeaturedListScreenViewModelDelegate.*
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface FeaturedListScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val featured: Flow<PagingData<Featured>> = flowOf()
    )

    sealed interface Effect {

    }

    sealed interface Event {
        object Refresh : Event
    }

}

@HiltViewModel
class FeaturedListScreenViewModel @Inject constructor(
    getFeaturedListUseCase: GetFeaturedListUseCase
) : PlatformViewModel(), FeaturedListScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val featuredList = getFeaturedListUseCase()
        .pagingData
        .cachedIn(platformViewModelScope)

    override val state: StateFlow<State> = MutableStateFlow(State(featured = featuredList))

    override fun event(e: Event) {

    }

}