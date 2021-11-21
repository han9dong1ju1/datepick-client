package app.hdj.datepick.android.ui.screens.others.featuredDetail

import android.content.Intent
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedDetailViewModelDelegate.Event.FetchFeaturedById
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedDetailViewModelDelegate.Event.ServePreviousFeaturedData
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.domain.fold
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.verticalMargin


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturedDetailScreen(
    id: Long? = null,
    prevFeatured: Featured? = null,
    vm: FeaturedDetailViewModelDelegate = hiltViewModel<FeaturedDetailViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val lazyListState = rememberLazyListState()

    val context = LocalContext.current

    SideEffect {
        if (prevFeatured != null) {
            event(ServePreviousFeaturedData(prevFeatured))
        } else if (id != null) {
            event(FetchFeaturedById(id))
        }
    }

    effect.collectInLaunchedEffect {
        when (it) {
            is FeaturedDetailViewModelDelegate.Effect.OpenShareMenu -> {
                context.startActivity(Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, it.link)
                    type = "text/plain"
                }, "공유"))
            }
        }
    }

    val scrollBehavior = remember {
        TopAppBarDefaults.pinnedScrollBehavior()
    }

    BaseScaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetMediumTopAppBar(
                navigationIcon = { TopAppBarBackButton() },
                title = {
                    state.featured.onSucceedComposable { Text(text = it.title) }
                }, scrollBehavior = scrollBehavior
            )
        }
    ) {

        LazyColumn(
            state = lazyListState,
            content = {

                item {
                    state.content.onSucceedComposable {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            lineHeight = 24.sp,
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        text = "이 코스 어때요?", style = MaterialTheme.typography.headlineSmall
                    )
                }

                item {

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(20.dp)
                    ) {

                        items(4) {

                            Box(
                                modifier = Modifier
                                    .size(200.dp)
                                    .background(MaterialTheme.colorScheme.onSurface.copy(0.2f))
                            )

                            if (it != 3) Spacer(Modifier.width(20.dp))

                        }
                    }
                }

                verticalMargin(80.dp)

            }
        )

    }

}

@Composable
@Preview
fun FeaturedDetailScreenPreview() {
    BaseTheme {
        FeaturedDetailScreen(vm = fakeFeaturedDetailViewModel())
    }
}