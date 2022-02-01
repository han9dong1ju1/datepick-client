package app.hdj.datepick.android.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.Header
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.settings.SettingsScreenViewModel
import app.hdj.datepick.presentation.settings.SettingsScreenViewModelDelegate
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopBar
import app.hdj.datepick.ui.components.ListItem
import app.hdj.datepick.ui.components.TopAppBarBackButton
import com.google.accompanist.insets.navigationBarsHeight

@Composable
fun SettingsScreen(
    vm: SettingsScreenViewModelDelegate = hiltViewModel<SettingsScreenViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

    val settingList = rememberSettingList(
        state,
        event,
        navController
    )

    BaseScaffold(
        topBar = {
            BaseTopBar(navigationIcon = {
                TopAppBarBackButton()
            }, title = {
                Text("설정")
            })
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it)) {

            settingList.forEachIndexed { index, settingGroup ->

                item {
                    if (index != 0)
                        Divider(
                            modifier = Modifier.padding(20.dp),
                            color = MaterialTheme.colors.onBackground.copy(0.05f)
                        )
                    Header(settingGroup.header)
                }

                items(settingGroup.settings) { item ->

                    when (item) {
                        is SettingItem.NormalItem -> {
                            ListItem(
                                leftSideUi =
                                item.icon?.let {
                                    { Icon(it, null) }
                                },
                                title = item.title,
                                subtitle = item.description,
                                onClick = { item.onSelect() }
                            )
                        }
                        is SettingItem.SwitchItem -> {
                            ListItem(
                                leftSideUi = item.icon?.let {
                                    { Icon(it, null) }
                                },
                                rightSideUi = { Switch(checked = item.value, onCheckedChange = null) },
                                title = item.title,
                                subtitle = item.description,
                                onClick = { item.onSelect() }
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.navigationBarsHeight(20.dp))
            }

        }

    }

}