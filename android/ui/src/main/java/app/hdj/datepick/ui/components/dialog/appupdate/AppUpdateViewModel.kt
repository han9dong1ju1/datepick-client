package app.hdj.datepick.ui.components.dialog.appupdate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.components.dialog.appupdate.AppUpdateViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.utils.InAppUpdateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


fun fakeAppUpdateViewModel() = object : AppUpdateViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface AppUpdateViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State

    sealed class Effect {

    }

    sealed class Event {

    }

}

@HiltViewModel
class AppUpdateViewModel @Inject constructor(
    inAppUpdateManager: InAppUpdateManager
) : ViewModel(), AppUpdateViewModelDelegate {

    override val state = inAppUpdateManager.appUpdateFlow
        .map { State() }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            State()
        )

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}