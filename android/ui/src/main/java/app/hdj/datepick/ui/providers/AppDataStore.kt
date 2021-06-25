package app.hdj.datepick.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import app.hdj.shared.client.data.datastore.AppDataStore

val LocalAppDataStore = staticCompositionLocalOf<AppDataStore> {
    error("Not Provided!")
}

@Composable
fun ProvideAppDataStore(dataStore : AppDataStore, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppDataStore provides dataStore) {
        content()
    }
}