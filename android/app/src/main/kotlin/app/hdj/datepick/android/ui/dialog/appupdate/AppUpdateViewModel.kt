package app.hdj.datepick.android.ui.dialog.appupdate

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.dialog.appupdate.AppUpdateViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import com.google.android.play.core.ktx.AppUpdateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakeAppUpdateViewModel() = object : AppUpdateViewModelDelegate {

    init {

    }

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface AppUpdateViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val appUpdateResult: AppUpdateResult? = null
    )

    sealed class Effect {

    }

    sealed class Event {
        data class RequestUpdate(val activity: Activity, val result: AppUpdateResult.Available) :
            Event()

        data class CompleteUpdate(val result: AppUpdateResult.Downloaded) :
            Event()
    }

}

@HiltViewModel
class AppUpdateViewModel @Inject constructor(

) : ViewModel(), AppUpdateViewModelDelegate {

    override val state = TODO()

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.RequestUpdate -> TODO()
                is Event.CompleteUpdate -> TODO()
            }
        }
    }

}