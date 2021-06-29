package app.hdj.datepick.ui.components.screens.others.splash

import androidx.lifecycle.ViewModel
import app.hdj.datepick.ui.components.screens.others.splash.SplashViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


fun fakeSplashViewModel() = object : SplashViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface SplashViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State()

    sealed class Effect

    sealed class Event

}

@HiltViewModel
@OptIn(FlowPreview::class)
class SplashViewModel @Inject constructor(

) : ViewModel(), SplashViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    override val state = MutableStateFlow(State())

    override fun event(event: Event) {

    }

}