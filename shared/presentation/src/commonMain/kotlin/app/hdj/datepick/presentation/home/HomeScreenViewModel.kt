package app.hdj.datepick.presentation.home

import app.hdj.datepick.domain.isStateFailed
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.usecase.featured.GetFeaturedListUseCase
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.home.HomeScreenViewModelDelegate.*
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

interface HomeScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    class State(
        val featuredList: List<Featured> = emptyList()
    )

    sealed class Effect {
        object FeaturedError : Effect()
    }

    class Event {

    }

}

@Singleton
class HomeScreenViewModel @Inject constructor(
    getFeaturedListUseCase: GetFeaturedListUseCase
) : PlatformViewModel(), HomeScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>()
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val featuredList = getFeaturedListUseCase(Unit).onEach {
        if (it.isStateFailed()) effectChannel.send(Effect.FeaturedError)
    }

    override val state: StateFlow<State> = combine(
        featuredList,
        flowOf(true),
    ) { featuredListState, _ ->
        State(featuredListState.getDataOrNull().orEmpty())
    }.asStateFlow(
        State(),
        platformViewModelScope
    )

    override fun event(e: Event) {
        when (e) {

        }
    }
}