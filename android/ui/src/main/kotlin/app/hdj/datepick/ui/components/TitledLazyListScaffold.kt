package app.hdj.datepick.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TitledLazyListScaffold(
    lazyListState: LazyListState = rememberLazyListState(),
    title: @Composable () -> Unit,
    expandedTitle: @Composable () -> Unit,
    enableDivider: Boolean = false,
    navIcons: (@Composable () -> Unit)? = null,
    topAppBarActions: @Composable RowScope.() -> Unit = {},
    listContent: LazyListScope.() -> Unit
) {

    val visibleIndex = lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0

    DatePickScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {

                DatePickTopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = {
                        AnimatedVisibility(
                            visible = visibleIndex != 0,
                            enter = fadeIn() + slideInVertically({ it / 2 }),
                            exit = fadeOut() + slideOutVertically({ it / 2 })
                        ) {
                            title()
                        }
                    },
                    actions = topAppBarActions,
                    navigationIcon = navIcons
                )

                if (enableDivider) {
                    AnimatedVisibility(
                        visible = visibleIndex != 0,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Divider()
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = lazyListState
            ) {
                item {
                    expandedTitle()
                }

                listContent()
            }

        }
    }

}