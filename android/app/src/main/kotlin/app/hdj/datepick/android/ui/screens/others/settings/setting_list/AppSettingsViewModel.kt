package app.hdj.datepick.android.ui.screens.others.settings.setting_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.screens.others.settings.setting_list.AppSettingsViewModelDelegate.*
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakeSettingListViewModel() = object : AppSettingsViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface AppSettingsViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(
        val isDynamicColorEnabled : Boolean = false
    )

    sealed class Effect {

    }

    sealed class Event {
        class SetDynamicColor(val isEnabled : Boolean) : Event()
    }

}

@HiltViewModel
class AppSettingsViewModel @Inject constructor(
    private val appSettings: AppSettings
) : ViewModel(), AppSettingsViewModelDelegate {

    override val state: StateFlow<State> = combine(
        appSettings.isDynamicThemeEnabled,
        flowOf(false)
    ) { isDynamicThemeEnabled, _ ->
        State(isDynamicThemeEnabled)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        State()
    )

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.SetDynamicColor -> appSettings.setDynamicThemeEnabled(event.isEnabled)
            }
        }
    }

}