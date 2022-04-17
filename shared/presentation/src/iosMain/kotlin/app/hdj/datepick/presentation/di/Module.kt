package app.hdj.datepick.presentation.di

import app.hdj.datepick.presentation.main.HomeScreenViewModel
import org.koin.dsl.module

val presentationModule = module {
    single {
        HomeScreenViewModel(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}