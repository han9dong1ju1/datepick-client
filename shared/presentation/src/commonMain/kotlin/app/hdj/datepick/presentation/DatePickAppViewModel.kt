@file:OptIn(FlowPreview::class)

package app.hdj.datepick.presentation

import app.hdj.datepick.domain.*
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.model.auth.RefreshTokenResult
import app.hdj.datepick.domain.model.auth.RefreshTokenResult.*
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.domain.usecase.auth.RefreshTokenUseCase
import app.hdj.datepick.domain.usecase.user.GetLatestMeUseCase
import app.hdj.datepick.domain.usecase.user.ObserveMeUseCase
import app.hdj.datepick.presentation.DatePickAppViewModelDelegate.*
import app.hdj.datepick.presentation.utils.onlyAtSuccess
import app.hdj.datepick.presentation.utils.toLoadState
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import io.ktor.client.plugins.*
import io.ktor.http.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface DatePickAppViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val me: User? = null,
        val appLoadingState: LoadState<Unit> = loading(),
        val appTheme: AppSettings.AppTheme? = null,
    ) {
        val isLoading get() = appLoadingState.isStateLoading()
    }

    sealed class Effect {
        object Error : Effect()
        object UserBanned : Effect()
    }

    sealed class Event {

        object RetryInitializing : Event()
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

    private val appLoadingState = MutableStateFlow<LoadState<Unit>>(loading())

    override val state: StateFlow<State> =
        combine(
            me,
            appLoadingState,
            appSettings.appTheme
        ) { me, refreshTokenState, appTheme ->
            State(me, refreshTokenState, appTheme)
        }.stateIn(platformViewModelScope, SharingStarted.Lazily, State())

    init {
        platformViewModelScope.launch {
            load()
        }
    }

    private suspend fun load() {
        refreshTokenUseCase(forceRefresh = false)
            .catch { effectChannel.send(Effect.Error) }
            .toLoadState()
            .onlyAtSuccess()
            .collect { state ->
                when (state.data) {
                    NotSigned -> appLoadingState.emit(LoadState.success(Unit))
                    NoNeedToRefresh, Refreshed -> loadUser()
                }
            }
    }

    private suspend fun loadUser() {
        getLatestMeUseCase()
            .toLoadState()
            .onEach { loadState ->
                if (loadState.isStateFailed()) {
                    val throwable = loadState.throwable
                    if (throwable is ClientRequestException) {
                        when (throwable.response.status) {
                            // If user is banned, open User Banned Dialog
                            HttpStatusCode.Forbidden -> effectChannel.send(Effect.UserBanned)
                            else -> effectChannel.send(Effect.Error)
                        }
                    } else effectChannel.send(Effect.Error)
                }
                appLoadingState.emit(loadState.map { })
            }.collect()
    }

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {
                is Event.UpdateAppTheme -> appSettings.updateAppTheme(e.appTheme)
                Event.RetryInitializing -> load()
            }
        }
    }

}