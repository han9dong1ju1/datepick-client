package app.hdj.datepick.android.ui.components.screens.others.settings.setting_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.settings.setting_list.SettingsViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakeSettingListViewModel() = object : SettingsViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface SettingsViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(
    )

    sealed class Effect {

    }

    sealed class Event {
    }

}

@HiltViewModel
class SettingListViewModel @Inject constructor(
) : ViewModel(), SettingsViewModelDelegate {

    override val state: StateFlow<State> = TODO()

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {

            }
        }
    }

}