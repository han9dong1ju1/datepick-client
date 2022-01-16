package app.hdj.datepick.android.ui.screens.main.diary

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.list.DiaryListItem
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.domain.model.diary.Diary
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopBar
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun DiaryScreen(vm: DiaryViewModelDelegate = hiltViewModel<DiaryViewModel>()) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current

    var longClickedDiary by remember {
        mutableStateOf<Offset?>(null)
    }

    BaseScaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.navigationBars,
                    additionalBottom = 56.dp
                )
            ),
        topBar = {
            Column {
                BaseTopBar(
                    title = {
                        Text("다이어리")
                    }
                )

            }
        }
    ) {

        LazyVerticalGrid(
            modifier = Modifier.padding(it),
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {

            items(
                (0..10).map {
                    object : Diary {
                        override val id: Long
                            get() = System.currentTimeMillis()
                    }
                }
            ) {
                DiaryListItem(it) {
                    navController.navigateRoute(
                        AppNavigationGraph.DiaryDetail.graphWithArgument(it)
                    )
                }
            }

        }

    }

}