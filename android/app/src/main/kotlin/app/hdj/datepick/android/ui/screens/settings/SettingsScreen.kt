package app.hdj.datepick.android.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.Header
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.settings.SettingsScreenViewModel
import app.hdj.datepick.presentation.settings.SettingsScreenViewModelDelegate
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.ListItem
import app.hdj.datepick.ui.components.TopAppBarBackButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun SettingsScreen(
    navigator: DestinationsNavigator
) {
    SettingsScreenContent(
        navigator,
        hiltViewModel<SettingsScreenViewModel>()
    )
}

@Composable
private fun SettingsScreenContent(
    navigator: DestinationsNavigator,
    vm: SettingsScreenViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    val settingList = rememberSettingList(
        state,
        event,
        navigator
    )

    BaseScaffold(
        topBar = {
            InsetTopBar(navigationIcon = {
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
                    Header(settingGroup.header, color = MaterialTheme.colors.secondary)
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
                Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                Spacer(modifier = Modifier.height(20.dp))
            }

        }

    }

}