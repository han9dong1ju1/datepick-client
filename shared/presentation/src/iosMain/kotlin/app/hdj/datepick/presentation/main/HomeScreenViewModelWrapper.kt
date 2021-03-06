package app.hdj.datepick.presentation.main

import com.rickclephas.kmp.nativecoroutines.asNativeFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeScreenViewModelWrapper : KoinComponent {

    private val viewModel : HomeScreenViewModel by inject()

    val state = viewModel.state.asNativeFlow()
    val effect = viewModel.effect.asNativeFlow()

    fun event(e : HomeScreenViewModelDelegate.Event) {
        viewModel.event(e)
    }

}