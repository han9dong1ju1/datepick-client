package app.hdj.datepick.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.datepick.ui.DatePickAppViewModelDelegate.*
import app.hdj.shared.client.domain.repo.SettingRepository
import app.hdj.shared.client.domain.entity.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface DatePickAppViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val appTheme: AppTheme = AppTheme.SYSTEM
    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class DatePickAppViewModel @Inject constructor(
    settingRepository: SettingRepository
) : ViewModel(), DatePickAppViewModelDelegate {

    override val state: StateFlow<State> = combine(
        settingRepository.getAppTheme(),
        flowOf("")
    ) { appTheme, _ ->
        State(appTheme = appTheme)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        State()
    )

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override fun event(event: Event) = Unit

}