package app.hdj.datepick.android.ui.screens.others.appSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.screens.others.appSettings.AppSettingsViewModelDelegate.*
import app.hdj.datepick.domain.model.settings.AppTheme
import app.hdj.datepick.domain.repository.SettingsRepository
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakeAppSettingsViewModel() = object : AppSettingsViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface AppSettingsViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(
        val appTheme: AppTheme = AppTheme.System
    )

    sealed class Effect {

    }

    sealed class Event {
        class UpdateTheme(val theme: AppTheme) : Event()
    }

}

@HiltViewModel
class AppSettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel(), AppSettingsViewModelDelegate {

    override val state: StateFlow<State> =
        combine(settingsRepository.theme, flowOf(false)) { theme, _ ->
            State(theme)
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
                is Event.UpdateTheme -> settingsRepository.updateTheme(event.theme)
            }
        }
    }

}