package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.data.model.User
import app.hdj.datepick.data.model.fakeUser
import com.google.accompanist.insets.ProvideWindowInsets
import com.russhwolf.settings.ExperimentalSettingsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalSettingsApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun ProvideBasicsForPreview(
    meState: app.hdj.datepick.domain.StateData<app.hdj.datepick.data.model.User> = app.hdj.datepick.domain.StateData.Success(
        app.hdj.datepick.data.model.fakeUser()
    ),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalMeState provides meState,
        LocalAppNavController provides rememberNavController()
    ) {
        ProvideWindowInsets(content = content)
    }

}