package app.hdj.datepick.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TitledLazyListScaffold(
    title: @Composable () -> Unit,
    expandedTitle: @Composable () -> Unit,
    enableDivider: Boolean = true,
    navIcons: (@Composable () -> Unit)? = null,
    topAppBarActions: @Composable RowScope.() -> Unit = {},
    listContent: LazyListScope.() -> Unit
) {

    val lazyListState = rememberLazyListState()

    val visibleIndex = lazyListState.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0

    val coroutineScope = rememberCoroutineScope()

    DatePickScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {

                DatePickTopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(0)
                            }
                        },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding()),
            state = lazyListState
        ) {
            item {
                expandedTitle()
            }

            listContent()
        }
    }

}