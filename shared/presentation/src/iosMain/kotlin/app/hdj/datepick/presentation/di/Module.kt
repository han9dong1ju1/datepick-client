package app.hdj.datepick.presentation.di

import app.hdj.datepick.presentation.home.HomeScreenViewModel
import app.hdj.datepick.presentation.home.HomeScreenViewModelWrapper
import org.koin.dsl.module

val presentationModule = module {
    single {
        HomeScreenViewModel(
            get()
        )
    }
}