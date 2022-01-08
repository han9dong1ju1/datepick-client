package app.hdj.datepick.android.ui.screens.others.userProfileEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.screens.others.userProfileEdit.UserProfileEditViewModelDelegate.*
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.user.UserGender
import app.hdj.datepick.domain.usecase.user.UnregisterMeUseCase
import app.hdj.datepick.domain.usecase.user.UpdateMeUseCase
import app.hdj.datepick.domain.usecase.user.params.UserProfileRequestParams
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.utils.io.core.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakeUserProfileEditViewModel() = object : UserProfileEditViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface UserProfileEditViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(
    )

    sealed class Effect {
        object UnregisterCompleted : Effect()
        object ProfileUpdateCompleted : Effect()
    }

    sealed class Event {
        data class Unregister(val reason: String) : Event()
        data class UpdateProfile(
            val nickname: String,
            val gender: UserGender?,
            val image: Input? = null
        ) : Event()
    }

}

@HiltViewModel
class UserProfileEditViewModel @Inject constructor(
    unregisterMeUseCase: UnregisterMeUseCase,
    private val updateMeUseCase: UpdateMeUseCase,
) : ViewModel(), UserProfileEditViewModelDelegate {

    private val unregisterTrigger = MutableStateFlow<String?>(null)
    private val unregisterState = unregisterTrigger.filterNotNull()
        .flatMapConcat { unregisterMeUseCase(UnregisterMeUseCase.Param(it)) }
        .onEach { if (it.isStateSucceed()) effectChannel.send(Effect.UnregisterCompleted) }

    private val updateTrigger = MutableStateFlow<UserProfileRequestParams?>(null)
    private val updateState = updateTrigger.filterNotNull()
        .flatMapConcat { updateMeUseCase(it) }
        .onEach { if (it.isStateSucceed()) effectChannel.send(Effect.ProfileUpdateCompleted) }

    override val state: StateFlow<State> = combine(unregisterState, updateState) { _, _ ->
        State()
    }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.Unregister -> unregisterTrigger.emit(event.reason)
                is Event.UpdateProfile -> updateTrigger.emit(
                    UserProfileRequestParams(
                        nickname = event.nickname,
                        gender = event.gender,
                        image = event.image
                    )
                )
            }
        }
    }

}