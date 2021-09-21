package app.hdj.datepick.android.ui.screens.others.user

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.ui.components.DatePickScaffold

@Composable
fun UserProfileEditScreen(vm: UserProfileEditViewModelDelegate = hiltViewModel()) {

    val navController = LocalAppNavController.current

    DatePickScaffold(
        topBar = {

        },

        ) {


    }

}