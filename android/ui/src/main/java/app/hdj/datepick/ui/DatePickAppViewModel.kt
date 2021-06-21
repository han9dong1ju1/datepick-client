package app.hdj.datepick.ui

import androidx.lifecycle.ViewModel
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.datepick.ui.DatePickAppViewModelDelegate.*
import app.hdj.shared.client.domain.entity.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface DatePickAppViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val courses: List<Course>,
    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class DatePickAppViewModel @Inject constructor(

) : ViewModel(), DatePickAppViewModelDelegate {

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    override val effect: Flow<Effect>
        get() = TODO("Not yet implemented")

    override fun event(event: Event) {
        TODO("Not yet implemented")
    }

}