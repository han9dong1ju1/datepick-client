package app.hdj.datepick.domain.model.settings

sealed interface AppTheme {

    object Light : AppTheme
    object Dark : AppTheme
    object System : AppTheme

}