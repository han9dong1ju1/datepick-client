package app.hdj.datepick.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.datepick.ui.DatePickAppViewModelDelegate.*
import app.hdj.shared.client.data.api.Authenticator
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.repo.SettingRepository
import app.hdj.shared.client.domain.entity.AppTheme
import app.hdj.shared.client.domain.entity.User
import app.hdj.shared.client.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface DatePickAppViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val appTheme: AppTheme = AppTheme.SYSTEM,
        val me: StateData<User> = StateData.Loading()
    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class DatePickAppViewModel @Inject constructor(
    authenticator: Authenticator,
    settingRepository: SettingRepository,
    userRepository: UserRepository
) : ViewModel(), DatePickAppViewModelDelegate {

    private val userState = flow { emit(authenticator.currentUser()) }
        .flatMapConcat {
            if (it != null) userRepository.getMe()
            else flowOf(StateData.Failed())
        }

    override val state: StateFlow<State> = combine(
        settingRepository.getAppTheme(),
        userState
    ) { appTheme, me ->
        State(
            appTheme = appTheme,
            me = me
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        State()
    )

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override fun event(event: Event) = Unit

}