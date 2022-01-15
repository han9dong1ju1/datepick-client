package app.hdj.datepick.android.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.SearchBoxState.SearchBoxUiState
import app.hdj.datepick.ui.components.Chip
import app.hdj.datepick.ui.components.Header
import com.google.accompanist.flowlayout.FlowRow

@Stable
class SearchBoxState(
    initialUiState: SearchBoxUiState = SearchBoxUiState.Collapsed
) {

    var uiState by mutableStateOf(initialUiState)

    val isExpanded get() = uiState == SearchBoxUiState.Expanded

    enum class SearchBoxUiState {
        Collapsed, Expanded
    }

    fun collapse() {
        uiState = SearchBoxUiState.Collapsed
    }

    fun expand() {
        uiState = SearchBoxUiState.Expanded
    }

    fun toggle() {
        if (uiState == SearchBoxUiState.Collapsed) expand()
        else collapse()
    }

}

@Composable
fun rememberSearchBoxState(initialUiState: SearchBoxUiState = SearchBoxUiState.Collapsed) =
    remember {
        SearchBoxState(initialUiState)
    }


@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    state: SearchBoxState = rememberSearchBoxState()
) {

    val elevation by animateDpAsState(targetValue = if (state.isExpanded) 0.dp else 10.dp)
    val cornerRadius by animateDpAsState(targetValue = if (state.isExpanded) 0.dp else 40.dp)
    val scale by animateFloatAsState(targetValue = if (state.isExpanded) 1f else 0.9f)

    Box(
        modifier = modifier
    ) {

        Surface(
            onClick = {
                if (state.uiState == SearchBoxUiState.Collapsed) state.expand()
            },
            modifier = Modifier
                .graphicsLayer {
                    scaleX *= scale
                    scaleY *= scale
                },
            shape = RoundedCornerShape(cornerRadius),
        ) {
            AnimatedContent(
                modifier = Modifier.animateContentSize(),
                targetState = state.isExpanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150, 150)) with
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        IntSize(targetSize.width, initialSize.height) at 150
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        IntSize(initialSize.width, targetSize.height) at 150
                                        durationMillis = 300
                                    }
                                }
                            }
                }
            ) {
                if (it) {
                    SearchBoxExpandedUi(state = state)
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = state::expand) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "검색하기",
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        }

    }

}

@Composable
private fun SearchBoxExpandedUi(
    state: SearchBoxState
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = state::collapse) {
                Icon(
                    imageVector = Icons.Rounded.Close, contentDescription = null
                )
            }
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "검색어를 입력해주세요.") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledBorderColor = Color.Unspecified,
                    focusedBorderColor = Color.Unspecified,
                    unfocusedBorderColor = Color.Unspecified
                )
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.onBackground.copy(0.05f))
        )

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            Header("필터")
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                crossAxisSpacing = 12.dp, mainAxisSpacing = 12.dp
            ) {
                listOf(
                    "크리스마스", "기념일", "생일파티", "액티비티",
                    "맛있는 음식", "영화", "여행", "기념일"
                ).forEach { tag ->
                    Chip(
                        modifier = Modifier.height(40.dp),
                        text = tag,
                        isSelected = false
                    ) {

                    }
                }
            }

            Header("필터")
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                crossAxisSpacing = 12.dp, mainAxisSpacing = 12.dp
            ) {
                listOf(
                    "크리스마스", "기념일", "생일파티", "액티비티",
                    "맛있는 음식", "영화", "여행", "기념일"
                ).forEach { tag ->
                    Chip(
                        modifier = Modifier.height(40.dp),
                        text = tag,
                        isSelected = false
                    ) {

                    }
                }
            }

        }

    }

}