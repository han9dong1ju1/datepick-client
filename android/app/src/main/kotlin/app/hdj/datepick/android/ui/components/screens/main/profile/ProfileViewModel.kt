package app.hdj.datepick.android.ui.components.screens.main.profile

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun fakeProfileViewModel() = object : ProfileViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface ProfileViewModelDelegate :
    ViewModelDelegate<State, Effect, Event> {

    class State()

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : ViewModel(), ProfileViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    override val state: StateFlow<State> = MutableStateFlow(State())

    override fun event(event: Event) {
        viewModelScope.launch {

        }
    }


}