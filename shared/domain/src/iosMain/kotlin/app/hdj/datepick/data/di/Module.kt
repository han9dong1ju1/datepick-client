package app.hdj.datepick.data.di

import app.hdj.datepick.domain.usecase.featured.GetTopFeaturedListUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetTopFeaturedListUseCase(get()) }
}