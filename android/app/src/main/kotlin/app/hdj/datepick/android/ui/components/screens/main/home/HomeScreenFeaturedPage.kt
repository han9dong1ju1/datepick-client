package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.android.ui.preview.FakeFeaturedProvider
import app.hdj.datepick.ui.components.Shimmer
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.*
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.data.model.Featured
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun HomeScreenFeaturedPage(
    state: app.hdj.datepick.domain.StateData<List<app.hdj.datepick.data.model.Featured>>,
    featuredPagerState: PagerState = rememberPagerState(pageCount = if (state is app.hdj.datepick.domain.StateData.Success) state.data.size else 0)
) {

    AnimatedContent(targetState = state) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            when (it) {
                is app.hdj.datepick.domain.StateData.Success -> {

                    HorizontalPager(
                        modifier = Modifier.fillMaxSize(),
                        state = featuredPagerState
                    ) { position ->

                        val data = it.data[position]

                        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                            val (titleRef, subtitleRef) = createRefs()

                            Image(
                                painter = rememberUrlImagePainter(request = data.photoUrl),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(0.4f))
                            )

                            Text(
                                text = data.title,
                                modifier = Modifier.constrainAs(
                                    titleRef,
                                    s2s(subtitleRef) + b2t(subtitleRef, margin = 10.dp)
                                ),
                                style = MaterialTheme.typography.h1,
                                color = Color.White
                            )

                            Text(
                                text = data.description,
                                modifier = Modifier.constrainAs(
                                    subtitleRef,
                                    s2s(margin = 20.dp) + b2b(margin = 40.dp + 20.dp)
                                ),
                                style = MaterialTheme.typography.subtitle2,
                                color = Color.White.copy(0.5f)
                            )

                        }

                    }

                    HorizontalPagerIndicator(
                        modifier = Modifier
                            .height(40.dp)
                            .align(Alignment.BottomCenter),
                        pagerState = featuredPagerState,
                        activeColor = Color.White,
                        inactiveColor = Color.White.copy(0.2f)
                    )

                }
                is app.hdj.datepick.domain.StateData.Loading -> {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(0.4f))
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        Shimmer(modifier = Modifier.size(200.dp, 24.dp).clip(MaterialTheme.shapes.small), color = Color.White)
                        Spacer(modifier = Modifier.height(10.dp))
                        Shimmer(modifier = Modifier.size(240.dp, 24.dp).clip(MaterialTheme.shapes.small), color = Color.White)
                        Spacer(modifier = Modifier.height(20.dp))
                        Shimmer(modifier = Modifier.size(120.dp, 14.dp).clip(MaterialTheme.shapes.small), color = Color.White)
                        Spacer(modifier = Modifier.height(40.dp))
                    }

                }
                is app.hdj.datepick.domain.StateData.Failed -> {

                }
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun HomeScreenFeaturedPagePreview(
    @PreviewParameter(FakeFeaturedProvider::class) state: app.hdj.datepick.domain.StateData<List<app.hdj.datepick.data.model.Featured>>
) {
    DatePickTheme {
        HomeScreenFeaturedPage(state)
    }
}