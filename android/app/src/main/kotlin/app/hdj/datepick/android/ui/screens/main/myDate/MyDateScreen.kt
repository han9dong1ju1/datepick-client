package app.hdj.datepick.android.ui.screens.main.myDate

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.list.MyDateListItem
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopBar
import app.hdj.datepick.utils.PlatformLogger
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.random.Random

private fun createNewDataList(startDate: LocalDate): List<LocalDate> {
    return listOf(startDate + DatePeriod(days = Random.nextInt(10, 20))) + (1..Random.nextInt(2, 10)).map {
        startDate + DatePeriod(days = it)
    }
}

@Composable
fun MyDateScreen() {

    val navController = LocalAppNavController.current

    var durationList by remember {
        mutableStateOf(
            createNewDataList(LocalDate(2022, 1, 1)).sorted()
        )
    }

    BaseScaffold(
        topBar = {
            BaseTopBar(
                title = {
                    Text("내 데이트")
                },
                actions = {
                    IconButton({
                        navController.navigateRoute(AppNavigationGraph.CreateCourse)
                    }) {
                        Icon(imageVector = Icons.Rounded.Add, null)
                    }
                }
            )
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it)) {

            durationList.forEachIndexed { index, localDate ->

                if (
                    localDate.monthNumber != durationList.getOrNull(index - 1)?.monthNumber ||
                    index == 0
                ) {
                    stickyHeader {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Column(
                                modifier = Modifier.width(80.dp)
                                    .align(Alignment.CenterStart),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Spacer(modifier = Modifier.height(20.dp))

                                Text(
                                    localDate.year.toString(),
                                    style = MaterialTheme.typography.caption,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colors.onBackground.copy(0.5f)
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Surface(
                                    shape = RoundedCornerShape(100.dp),
                                    color = MaterialTheme.colors.secondary.copy(0.2f)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(horizontal = 12.dp, vertical = 6.dp),
                                        text = "${localDate.monthNumber}월",
                                        style = MaterialTheme.typography.subtitle1,
                                        color = MaterialTheme.colors.secondary
                                    )
                                }
                            }

                        }
                    }

                }

                item {
                    Box(modifier = Modifier.padding(start = 80.dp, end = 10.dp)) {

                        MyDateListItem(modifier = Modifier.fillMaxWidth(), localDate) {

                        }

                        if (durationList.lastIndex == index) {
                            LaunchedEffect(true) {
                                durationList = (durationList + createNewDataList(localDate)).sorted()
                            }
                        }

                    }
                }

            }

        }

    }

}