package app.hdj.datepick.android.ui.screens.others.featuredDetail

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.MR
import app.hdj.datepick.android.ui.MarkdownText
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedDetailViewModelDelegate.Event.FetchFeaturedById
import app.hdj.datepick.android.utils.foldCrossfade
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.data.utils.res
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer


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

    BaseScaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            TopAppBar(
                navigationIcon = { TopAppBarBackButton() },
                title = {},
                actions = {
                    IconButton(onClick = {
                        event(FeaturedDetailViewModelDelegate.Event.CreateShareUrl)
                    }) {
                        Icon(imageVector = Icons.Rounded.Share, null)
                    }
                }
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(it),
            content = {

                item {
                    TopAppBar(
                        title = { state.featured.onSucceedComposable { Text(text = it.title) } }
                    )
                }

                item {
                    state.content.foldCrossfade(
                        onSuccess = {
                            MarkdownText(
                                markdown = it,
                                color = MaterialTheme.colors.onBackground,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                style = MaterialTheme.typography.body1
                            )
                        },
                        onLoading = { FeaturedContentShimmer() }
                    )
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
                        color = MaterialTheme.colors.onBackground.copy(
                            0.1f
                        ),
                        shape = RoundedCornerShape(4.dp),
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = MaterialTheme.colors.onBackground.copy(
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