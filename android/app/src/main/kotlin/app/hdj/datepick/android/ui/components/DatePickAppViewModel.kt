package app.hdj.datepick.android.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.datepick.android.ui.components.DatePickAppViewModelDelegate.*
import app.hdj.datepick.data.api.Authenticator
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.repo.SettingRepository
import app.hdj.datepick.data.model.settings.AppTheme
import app.hdj.datepick.data.model.User
import app.hdj.datepick.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface DatePickAppViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val appTheme: app.hdj.datepick.data.model.settings.AppTheme = app.hdj.datepick.data.model.settings.AppTheme.SYSTEM,
        val me: app.hdj.datepick.domain.StateData<app.hdj.datepick.data.model.User> = app.hdj.datepick.domain.StateData.Loading()
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
    authenticator: app.hdj.datepick.data.api.Authenticator,
    settingRepository: app.hdj.datepick.domain.repo.SettingRepository,
    userRepository: app.hdj.datepick.domain.repo.UserRepository
) : ViewModel(), DatePickAppViewModelDelegate {

    private val userState = flow {
        val firebaseUser = authenticator.runCatching { currentUser() }.getOrNull()
        emit(firebaseUser)
    }.flatMapConcat {
        if (it != null) userRepository.getMe()
        else flowOf(app.hdj.datepick.domain.StateData.Failed())
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