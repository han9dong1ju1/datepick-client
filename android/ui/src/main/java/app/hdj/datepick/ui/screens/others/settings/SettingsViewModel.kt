package app.hdj.datepick.ui.screens.others.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.screens.others.settings.SettingsViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.repo.SettingRepository
import app.hdj.shared.client.domain.entity.AppTheme
import app.hdj.shared.client.domain.entity.NotificationSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakeSettingsViewModel() = object : SettingsViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface SettingsViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val appTheme: AppTheme = AppTheme.SYSTEM,
        val notificationSettings: NotificationSettings = NotificationSettings()
    )

    sealed class Effect {

    }

    sealed class Event {
        data class ChangeTheme(val appTheme: AppTheme) : Event()
        data class UpdateNotificationSettings(
            val notificationSettings: NotificationSettings
        ) : Event()
    }

}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel(), SettingsViewModelDelegate {

    override val state: StateFlow<State> = combine(
        settingRepository.getAppTheme(),
        settingRepository.getNotificationSettings()
    ) { appTheme, notificationSettings ->
        State(appTheme, notificationSettings)
    }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.ChangeTheme -> {
                    settingRepository.updateAppTheme(event.appTheme)
                }
                is Event.UpdateNotificationSettings -> {
                    settingRepository.updateNotificationSettings(event.notificationSettings)
                }
            }
        }
    }

}