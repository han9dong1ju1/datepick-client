package app.hdj.datepick.android.ui.screens.others.diaryDetail

import androidx.lifecycle.ViewModel
import app.hdj.datepick.android.ui.screens.others.diaryDetail.DiaryDetailViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


fun fakeDiaryDetailViewModel() = object : DiaryDetailViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface DiaryDetailViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(

    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class DiaryDetailViewModel @Inject constructor(

) : ViewModel(), DiaryDetailViewModelDelegate {

    override val state: StateFlow<State> = MutableStateFlow(State())

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}