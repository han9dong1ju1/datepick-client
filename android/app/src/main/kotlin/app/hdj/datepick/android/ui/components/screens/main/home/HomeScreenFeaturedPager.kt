package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.providers.preview.FakeFeaturedPreviewProvider
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.components.DatePickPager
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.VerticalMargin
import app.hdj.datepick.ui.utils.rememberUrlImagePainter
import coil.size.Scale
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
private fun HomeScreenFeaturedPagerItem(featured: Featured) {

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberUrlImagePainter(request = featured.photoUrl) {
                scale(Scale.FILL)
            },
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.4f))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = featured.title,
                color = Color.White,
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = featured.description,
                color = Color.White,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(40.dp))

        }

    }

}

@Composable
fun HomeScreenFeaturedPager(list: List<Featured>) {

    val pagerState = rememberPagerState(pageCount = list.size, infiniteLoop = true)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {

        DatePickPager(
            list = list,
            pagerState = pagerState,
            autoScrollEnabled = true,
            modifier = Modifier.fillMaxWidth(),
        ) {
            HomeScreenFeaturedPagerItem(featured = it)
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp),
            activeColor = Color.White,
            indicatorHeight = 4.dp,
            indicatorWidth = 20.dp,
            spacing = 8.dp,
            pagerState = pagerState
        )

    }

}

@Composable
@Preview(showBackground = true)
fun HomeScreenFeaturedPagerPreview(
    @PreviewParameter(FakeFeaturedPreviewProvider::class) featuredList: List<Featured>
) {
    DatePickTheme {
        HomeScreenFeaturedPager(featuredList)
    }
}