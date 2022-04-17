package app.hdj.datepick.presentation

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.domain.usecase.auth.RefreshTokenUseCase
import app.hdj.datepick.domain.usecase.user.GetLatestMeUseCase
import app.hdj.datepick.domain.usecase.user.ObserveMeUseCase
import app.hdj.datepick.presentation.DatePickAppViewModelDelegate.*
import app.hdj.datepick.presentation.utils.toLoadState
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface DatePickAppViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val me: User? = null,
        val refreshTokenState: LoadState<Unit> = loading(),
        val appTheme: AppSettings.AppTheme? = null,
    ) {
        val isLoading get() = refreshTokenState.isStateLoading()
    }

    sealed class Effect {
        object ShowNetworkErrorDialog : Effect()
        object OpenMainScreen : Effect()
    }

    sealed class Event {

        class UpdateAppTheme(val appTheme: AppSettings.AppTheme) : Event()

    }

}

@HiltViewModel
class DatePickAppViewModel @Inject constructor(
    private val appSettings: AppSettings,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val getLatestMeUseCase: GetLatestMeUseCase,
    observeMeUseCase: ObserveMeUseCase,
) : PlatformViewModel(), DatePickAppViewModelDelegate {

    private val me = observeMeUseCase()

    private val refreshTokenState = MutableStateFlow<LoadState<Unit>>(loading())

    override val state: StateFlow<State> =
        combine(
            me,
            refreshTokenState,
            appSettings.appTheme
        ) { me, refreshTokenState, appTheme ->
            State(me, refreshTokenState, appTheme)
        }.stateIn(platformViewModelScope, SharingStarted.Lazily, State())

    init {
        platformViewModelScope.launch {
            fetchMe()
            refreshToken()
        }
    }

    private suspend fun refreshToken() {
        refreshTokenUseCase(forceRefresh = true)
            .toLoadState()
            .onEach { refreshTokenState.emit(it) }
            .collect()
    }

    private suspend fun fetchMe() {
        getLatestMeUseCase()
            .toLoadState()
            .collect()
    }

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {
                is Event.UpdateAppTheme -> appSettings.updateAppTheme(e.appTheme)
            }
        }
    }

}