package app.hdj.datepick.android.ui.screens.others.featuredDetail

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedDetailViewModelDelegate.Event.FetchFeaturedById
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedDetailViewModelDelegate.Event.ServePreviousFeaturedData
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.domain.mapOrNull
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.verticalMargin
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


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

    val collapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    BaseScaffold(modifier = Modifier.fillMaxSize()) {

        BaseCollapsingToolbarScaffold(
            state = collapsingToolbarScaffoldState,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                BaseCollapsingTopBar(
                    backgroundColor = Color.Transparent,
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        val progress = collapsingToolbarScaffoldState.toolbarState.progress

                        val iconColor = lerp(
                            MaterialTheme.colors.onSurface,
                            Color.White,
                            progress
                        )

                        TopAppBarBackButton(contentColor = iconColor)
                    },
                    background = {
                        val progress = collapsingToolbarScaffoldState.toolbarState.progress

                        Surface(
                            modifier = Modifier
                                .pin()
                                .fillMaxWidth()
                                .height(300.dp)
                        ) {
                            NetworkImage(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .graphicsLayer { alpha = progress },
                                url = state.featured.mapOrNull { it.photoUrl }
                            )
                        }
                    },
                    content = {
                        val progress = collapsingToolbarScaffoldState.toolbarState.progress
                        val textSize = (16 + (24 - 16) * progress).sp

                        val maxLine = if (progress > 0.8f) 1 else Int.MAX_VALUE
                        val fontColor = lerp(
                            MaterialTheme.colors.onSurface,
                            Color.White,
                            progress
                        )

                        Text(
                            modifier = Modifier.padding(it),
                            fontSize = textSize,
                            text = state.featured.mapOrNull { it.title }.orEmpty(),
                            maxLines = maxLine,
                            color = fontColor
                        )
                    },
                    titleWhenCollapsed = Alignment.BottomStart,
                    titleWhenExpanded = Alignment.BottomStart
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
                                style = MaterialTheme.typography.subtitle2
                            )
                        }
                    }

                    item {

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )

                    }

                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            text = "이 코스 어때요?", style = MaterialTheme.typography.h5
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
                                        .background(MaterialTheme.colors.onSurface.copy(0.2f))
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

}

@Composable
@Preview
fun FeaturedDetailScreenPreview() {
    BaseTheme {
        FeaturedDetailScreen(vm = fakeFeaturedDetailViewModel())
    }
}