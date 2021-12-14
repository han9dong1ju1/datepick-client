package app.hdj.datepick.android.ui.screens.others.diaryDetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetSmallTopAppBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.components.ViewPager
import app.hdj.datepick.ui.utils.extract

@Composable
fun DiaryDetailScreen(
    id: Long,
    vm: DiaryDetailViewModelDelegate = hiltViewModel<DiaryDetailViewModel>()
) {

    val (state, effect, event) = vm.extract()

    BaseScaffold(
        topBar = {
            InsetSmallTopAppBar(
                navigationIcon = { TopAppBarBackButton() }
            )
        }
    ) {

        val pages = listOf("", "", "", "")
        ViewPager(
            list = pages
        ) { item, index ->
            when (index) {
                0 -> DiaryCoverPageContent()
                pages.lastIndex -> DiaryLastPageContent()
                else -> DiaryPageContent()
            }
        }

    }
}