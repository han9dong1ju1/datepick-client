package app.hdj.datepick.android.ui.components

import androidx.lifecycle.ViewModel
import app.hdj.datepick.android.ui.components.DatePickAppViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

interface DatePickAppViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State()

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class DatePickAppViewModel @Inject constructor(

) : ViewModel(), DatePickAppViewModelDelegate {

    override val state: StateFlow<State> = TODO()

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override fun event(event: Event) = Unit

}