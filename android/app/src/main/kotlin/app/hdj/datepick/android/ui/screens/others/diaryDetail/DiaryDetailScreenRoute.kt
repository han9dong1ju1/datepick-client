package app.hdj.datepick.android.ui.screens.others.diaryDetail

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.DiaryDetail
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.DiaryDetail.ARGUMENT_DIARY_ID
import app.hdj.datepick.android.ui.screens.appNavigationComposable

fun NavGraphBuilder.diaryDetailScreen() {
    appNavigationComposable(DiaryDetail) {
        val id = it.arguments?.getLong(ARGUMENT_DIARY_ID)
        DiaryDetailScreen(id = requireNotNull(id))
    }
}