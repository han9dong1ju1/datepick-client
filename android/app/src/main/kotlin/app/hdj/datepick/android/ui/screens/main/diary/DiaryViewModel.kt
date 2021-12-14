package app.hdj.datepick.android.ui.screens.main.diary

import androidx.lifecycle.ViewModel
import app.hdj.datepick.android.ui.screens.main.diary.DiaryViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


fun fakeDiaryScreenViewModel() = object : DiaryViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface DiaryViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(

    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class DiaryViewModel @Inject constructor(

) : ViewModel(), DiaryViewModelDelegate {

    override val state: StateFlow<State> = MutableStateFlow(State())

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}