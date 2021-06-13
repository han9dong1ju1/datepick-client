package app.hdj.datepick.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class StateViewModel<STATE, EFFECT, EVENT> : ViewModel() {

    abstract val state : StateFlow<STATE>

    abstract val effect : StateFlow<EFFECT>

    abstract fun event(event : EVENT)

}