package app.hdj.datepick.ui.screens.setting

import androidx.lifecycle.ViewModel
import app.hdj.datepick.ui.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.annotation.meta.Exhaustive
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(

) : StateViewModel<SettingViewModel.State, SettingViewModel.Effect, SettingViewModel.Event>() {

    data class State(
        val showProgress: Boolean
    )

    class Effect {

    }

    sealed class Event {
        object RequestGoogleLogin : Event()
    }


    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    override val effect: StateFlow<Effect>
        get() = TODO("Not yet implemented")

    override fun event(event: Event) {

    }

}
