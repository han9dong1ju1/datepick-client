package app.hdj.datepick.android.ui.screens.others.createCourse.recommendedPlaces

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.hdj.datepick.android.ui.components.list.PlaceHorizontalListItem
import app.hdj.datepick.android.ui.components.list.PlaceHorizontalListItemShimmer
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.android.ui.screens.others.createCourse.CreateCourseViewModelDelegate
import app.hdj.datepick.android.utils.foldCrossfade
import app.hdj.datepick.ui.components.CustomScrollableTabRow
import app.hdj.datepick.ui.components.Tab
import app.hdj.datepick.ui.components.tabIndicatorOffset


@Composable
fun CreateCourseRecommendedPlacesScreen(
    navController: NavController,
    state: CreateCourseViewModelDelegate.State,
    event: (CreateCourseViewModelDelegate.Event) -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "추천 장소들을\n선택해보세요.",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state.selectedPlaces.size.toString(),
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 1.dp),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "개 장소가 선택되었습니다.",
                color = MaterialTheme.colorScheme.onBackground
                    .copy(0.5f)
                    .compositeOver(MaterialTheme.colorScheme.background),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(6.dp))
            IconButton(onClick = {
                navController.navigateRoute(AppNavigationGraph.CreateCourse.ShowSelectedPlaces)
            }) {
                Icon(imageVector = Icons.Rounded.ChevronRight, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        CustomScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = {
                Box(
                    Modifier
                        .tabIndicatorOffset(it[selectedTabIndex])
                        .height(4.dp)
                        .padding(horizontal = 10.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(10.dp)
                        )
                )
            },
            edgePadding = 10.dp
        ) {
            listOf("인기", "주변", "재미있는").forEachIndexed { index, label ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 14.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        state.recommendedPlaces.foldCrossfade(
            onLoading = {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState(), enabled = false)
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    PlaceHorizontalListItemShimmer()
                    Spacer(modifier = Modifier.width(20.dp))
                    PlaceHorizontalListItemShimmer()
                }
            },
            onSuccess = {
                LazyRow(
                    contentPadding = PaddingValues(start = 20.dp)
                ) {
                    items(it) {
                        PlaceHorizontalListItem(
                            place = it,
                            isSelected = state.selectedPlaces.contains(it),
                            onPlaceClicked = { place ->
                                event(CreateCourseViewModelDelegate.Event.SelectPlace(place))
                            }
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
        )
    }
}