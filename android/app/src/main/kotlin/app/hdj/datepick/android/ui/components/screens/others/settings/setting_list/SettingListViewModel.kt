package app.hdj.datepick.android.ui.components.screens.others.settings.setting_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.settings.setting_list.SettingsViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.datepick.domain.repo.SettingRepository
import app.hdj.datepick.data.model.settings.AppTheme
import app.hdj.datepick.data.model.settings.NotificationSettings
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

    data class State(
        val appTheme: app.hdj.datepick.data.model.settings.AppTheme = app.hdj.datepick.data.model.settings.AppTheme.SYSTEM,
        val notificationSettings: app.hdj.datepick.data.model.settings.NotificationSettings = app.hdj.datepick.data.model.settings.NotificationSettings()
    )

    sealed class Effect {

    }

    sealed class Event {
        data class ChangeTheme(val appTheme: app.hdj.datepick.data.model.settings.AppTheme) : Event()
        data class UpdateNotificationSettings(
            val notificationSettings: app.hdj.datepick.data.model.settings.NotificationSettings
        ) : Event()
    }

}

@HiltViewModel
class SettingListViewModel @Inject constructor(
    private val settingRepository: app.hdj.datepick.domain.repo.SettingRepository
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