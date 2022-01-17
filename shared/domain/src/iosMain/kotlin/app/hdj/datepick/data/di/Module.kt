package app.hdj.datepick.data.di

import app.hdj.datepick.domain.usecase.featured.GetFeaturedListUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetFeaturedListUseCase(get()) }
}