package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.User
import app.hdj.shared.client.domain.entity.fakeUser
import com.google.accompanist.insets.ProvideWindowInsets
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.MockSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalSettingsApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun ProvideBasicsForPreview(
    meState: StateData<User> = StateData.Success(fakeUser()),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalMeState provides meState,
        LocalAppNavController provides rememberNavController()
    ) {
        ProvideWindowInsets(content = content)
    }

}