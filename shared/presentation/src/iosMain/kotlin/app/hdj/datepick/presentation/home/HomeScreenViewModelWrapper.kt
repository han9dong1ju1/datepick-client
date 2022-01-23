package app.hdj.datepick.presentation.home

import app.hdj.datepick.presentation.main.home.HomeScreenViewModel
import app.hdj.datepick.presentation.main.home.HomeScreenViewModelDelegate
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