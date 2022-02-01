package app.hdj.datepick.utils.di

@OptIn(ExperimentalMultiplatform::class)
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@OptionalExpectation
expect annotation class Named(val value: String)