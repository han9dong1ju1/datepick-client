package app.hdj.datepick.presentation.di

import app.hdj.datepick.data.di.dataModule
import app.hdj.datepick.data.di.domainModule
import app.hdj.datepick.presentation.main.HomeScreenViewModel
import app.hdj.datepick.utils.di.utilsModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        dataModule,
        domainModule,
        presentationModule,
        utilsModule
    )
}