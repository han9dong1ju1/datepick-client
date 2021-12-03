package app.hdj.datepick.android.ui.screens.others.featuredDetail

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import app.hdj.datepick.MR
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.MarkdownText
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedDetailViewModelDelegate.Event.FetchFeaturedById
import app.hdj.datepick.android.utils.foldCrossfade
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.data.utils.res
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetLargeTopAppBar
import app.hdj.datepick.ui.components.InsetSmallTopAppBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import app.hdj.datepick.ui.utils.verticalMargin
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturedDetailScreen(
    id: Long,
    vm: FeaturedDetailViewModelDelegate = hiltViewModel<FeaturedDetailViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current

    SideEffect {
        event(FetchFeaturedById(id))
    }

    effect.collectInLaunchedEffect {
        when (it) {
            is FeaturedDetailViewModelDelegate.Effect.OpenShareMenu -> {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, it.link)
                }
                context.startActivity(
                    Intent.createChooser(
                        intent,
                        context.res(MR.strings.title_share)
                    )
                )
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
            InsetSmallTopAppBar(
                navigationIcon = { TopAppBarBackButton() },
                title = {},
                actions = {
                    IconButton(onClick = {
                        event(FeaturedDetailViewModelDelegate.Event.CreateShareUrl)
                    }) {
                        Icon(imageVector = Icons.Rounded.Share, null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(it),
            content = {

                item {
                    InsetLargeTopAppBar(
                        title = { state.featured.onSucceedComposable { Text(text = it.title) } }
                    )
                }

                item {
                    state.content.foldCrossfade(
                        onSuccess = {
                            MarkdownText(
                                markdown = it,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onLoading = { FeaturedContentShimmer() }
                    )
                }

                verticalMargin(80.dp)

                item {
                    LazyRow {


                    }
                }

            }
        )

    }

}

@Composable
private fun FeaturedContentShimmer() {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        val shimmerUi = @Composable { width: Dp?, height: Dp ->
            Spacer(
                modifier = Modifier
                    .height(height)
                    .run {
                        if (width == null) fillMaxWidth()
                        else width(width)
                    }
                    .placeholder(
                        visible = true,
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            0.1f
                        ),
                        shape = RoundedCornerShape(4.dp),
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = MaterialTheme.colorScheme.onBackground.copy(
                                0.2f
                            )
                        )
                    ),
            )
        }

        shimmerUi(null, 200.dp)
        Spacer(modifier = Modifier.height(20.dp))
        shimmerUi((200..300).random().dp, 30.dp)
        Spacer(modifier = Modifier.height(20.dp))
        repeat(5) {
            Spacer(modifier = Modifier.height(10.dp))
            shimmerUi((200..300).random().dp, 14.dp)
        }
    }
}