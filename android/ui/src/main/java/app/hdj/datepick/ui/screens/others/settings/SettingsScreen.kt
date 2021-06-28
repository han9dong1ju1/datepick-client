package app.hdj.datepick.ui.screens.others.settings

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.providers.LocalParentNavController
import app.hdj.datepick.ui.providers.ProvideBasicsForPreview
import app.hdj.datepick.ui.providers.ProvideParentNavController
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar

@Composable
fun SettingsScreen(
    vm: SettingsViewModelDelegate = hiltViewModel<SettingsViewModel>()
) {

    val (state, effect, event) = vm.extract()
    val navController = LocalParentNavController.current

    DatePickScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DatePickTopAppBar(
                title = {
                    Text(text = "설정")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, null)
                    }
                }
            )
        }
    ) {

    }

}

@Composable
@Preview
fun SettingsScreenPreview() {
    DatePickTheme {
        ProvideBasicsForPreview {
            SettingsScreen(fakeSettingsViewModel())
        }
    }
}