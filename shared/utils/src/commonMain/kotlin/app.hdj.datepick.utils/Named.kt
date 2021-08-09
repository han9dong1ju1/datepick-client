package app.hdj.datepick.utils

@OptIn(ExperimentalMultiplatform::class)
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@OptionalExpectation
expect annotation class Named(val value: String)