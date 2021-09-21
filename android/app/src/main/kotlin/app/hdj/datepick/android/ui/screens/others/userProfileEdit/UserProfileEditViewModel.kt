package app.hdj.datepick.android.ui.screens.others.userProfileEdit

import androidx.lifecycle.ViewModel
import app.hdj.datepick.android.ui.screens.others.userProfileEdit.UserProfileEditViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


fun fakeUserProfileEditViewModel() = object : UserProfileEditViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface UserProfileEditViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(

    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class UserProfileEditViewModel @Inject constructor(

) : ViewModel(), UserProfileEditViewModelDelegate {

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        TODO("Not yet implemented")
    }

}