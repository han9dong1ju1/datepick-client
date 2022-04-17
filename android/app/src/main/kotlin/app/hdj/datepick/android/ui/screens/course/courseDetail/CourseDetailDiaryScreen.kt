package app.hdj.datepick.android.ui.screens.course.courseDetail

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.FirstPage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.presentation.coursedetail.CourseDetailScreenViewModelDelegate
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetBottomBar
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.components.ViewPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
@Destination(
    navGraph = COURSE_SCREEN_NAV_GRAPH,
    start = true
)
fun CourseDetailDiaryScreen(
    navigator: DestinationsNavigator,
    parentVm: CourseDetailScreenViewModelDelegate
) {

    val (parentState, parentEffect, parentEvent) = parentVm.extract()

    val listState = rememberLazyListState()

    val pagerState = rememberPagerState()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        parentEvent(CourseDetailScreenViewModelDelegate.Event.DiaryScrolled(pagerState.currentPage))
    }

    BaseScaffold(
        bottomBar = {

            AnimatedVisibility(
                pagerState.currentPage != 0,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                InsetBottomBar {

                    Box(modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 20.dp)) {

                        IconButton(
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.FirstPage,
                                null
                            )
                        }

                        HorizontalPagerIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            activeColor = MaterialTheme.colors.onBackground,
                            inactiveColor = MaterialTheme.colors.onBackground.copy(0.1f),
                            indicatorWidth = 4.dp,
                            spacing = 8.dp,
                            pagerState = pagerState
                        )
                    }
                }
            }
        }
    ) {
        parentState.courseState.onSucceedComposable {
            Box(modifier = Modifier.fillMaxSize()) {

                ViewPager(
                    modifier = Modifier.fillMaxSize(),
                    list = listOf(0, 1, 2),
                    pagerState = pagerState
                ) { item, index ->
                    if (index == 0) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                NetworkImage(
                                    modifier = Modifier.size(40.dp)
                                        .clip(CircleShape)
                                        .border(
                                            BorderStroke(1.dp, MaterialTheme.colors.onBackground.copy(alpha = 0.1f)),
                                            shape = CircleShape
                                        ),
                                    url = it.user.imageUrl,
                                    onFailed = {
                                        Icon(
                                            modifier = Modifier.size(14.dp),
                                            imageVector = Icons.Rounded.AccountCircle,
                                            contentDescription = null,
                                            tint = MaterialTheme.colors.onBackground
                                        )
                                    }
                                )

                                Spacer(Modifier.height(20.dp))

                                Text(
                                    text = it.user.nickname,
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Center,
                                )

                                Spacer(Modifier.height(40.dp))

                                NetworkImage(
                                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                                        .width(200.dp)
                                        .heightIn(max = 300.dp),
                                    url = it.imageUrl
                                )

                                Spacer(modifier = Modifier.height(40.dp))

                                Text(
                                    text = it.title,
                                    style = MaterialTheme.typography.h3
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                it.meetAtInstant?.toLocalDateTime(TimeZone.currentSystemDefault())?.run {
                                    Text(
                                        "${year}년 ${monthNumber}월 ${dayOfMonth}일",
                                        color = Color.White.copy(0.5f),
                                        style = MaterialTheme.typography.body2,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    style = MaterialTheme.typography.body2,
                                    text = it.tags.joinToString(separator = ", ") { it.name },
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                )

                                Spacer(modifier = Modifier.height((56 * 2).dp))
                                Spacer(modifier = Modifier.navigationBarsPadding())

                            }


                        }
                    } else {
                        androidx.compose.foundation.lazy.LazyColumn(state = listState) {
                            item {
                                // lorem ipsum
                                Text(
                                    modifier = Modifier.padding(20.dp),
                                    text = """대통령은 국가의 독립·영토의 보전·국가의 계속성과 헌법을 수호할 책무를 진다. 국가는 대외무역을 육성하며, 이를 규제·조정할 수 있다. 저작자·발명가·과학기술자와 예술가의 권리는 법률로써 보호한다. 정기회의 회기는 100일을, 임시회의 회기는 30일을 초과할 수 없다. 국방상 또는 국민경제상 긴절한 필요로 인하여 법률이 정하는 경우를 제외하고는, 사영기업을 국유 또는 공유로 이전하거나 그 경영을 통제 또는 관리할 수 없다.

위원은 정당에 가입하거나 정치에 관여할 수 없다. 정부는 회계연도마다 예산안을 편성하여 회계연도 개시 90일전까지 국회에 제출하고, 국회는 회계연도 개시 30일전까지 이를 의결하여야 한다. 대한민국은 국제평화의 유지에 노력하고 침략적 전쟁을 부인한다. 모든 국민은 통신의 비밀을 침해받지 아니한다.

모든 국민은 법률이 정하는 바에 의하여 공무담임권을 가진다. 모든 국민은 능력에 따라 균등하게 교육을 받을 권리를 가진다. 국가는 주택개발정책등을 통하여 모든 국민이 쾌적한 주거생활을 할 수 있도록 노력하여야 한다. 교육의 자주성·전문성·정치적 중립성 및 대학의 자율성은 법률이 정하는 바에 의하여 보장된다.

대통령이 궐위된 때 또는 대통령 당선자가 사망하거나 판결 기타의 사유로 그 자격을 상실한 때에는 60일 이내에 후임자를 선거한다. 모든 국민은 고문을 받지 아니하며, 형사상 자기에게 불리한 진술을 강요당하지 아니한다. 국회는 상호원조 또는 안전보장에 관한 조약, 중요한 국제조직에 관한 조약, 우호통상항해조약, 주권의 제약에 관한 조약, 강화조약, 국가나 국민에게 중대한 재정적 부담을 지우는 조약 또는 입법사항에 관한 조약의 체결·비준에 대한 동의권을 가진다.

누구든지 병역의무의 이행으로 인하여 불이익한 처우를 받지 아니한다. 연소자의 근로는 특별한 보호를 받는다. 공무원은 국민전체에 대한 봉사자이며, 국민에 대하여 책임을 진다. 국회는 국민의 보통·평등·직접·비밀선거에 의하여 선출된 국회의원으로 구성한다. 대통령은 내란 또는 외환의 죄를 범한 경우를 제외하고는 재직중 형사상의 소추를 받지 아니한다.

나는 헌법을 준수하고 국가를 보위하며 조국의 평화적 통일과 국민의 자유와 복리의 증진 및 민족문화의 창달에 노력하여 대통령으로서의 직책을 성실히 수행할 것을 국민 앞에 엄숙히 선서합니다. 헌법재판소 재판관은 탄핵 또는 금고 이상의 형의 선고에 의하지 아니하고는 파면되지 아니한다. 모든 국민은 거주·이전의 자유를 가진다.

감사위원은 원장의 제청으로 대통령이 임명하고, 그 임기는 4년으로 하며, 1차에 한하여 중임할 수 있다. 대통령은 취임에 즈음하여 다음의 선서를 한다. 국가는 법률이 정하는 바에 의하여 재외국민을 보호할 의무를 진다. 모든 국민은 법률이 정하는 바에 의하여 국방의 의무를 진다. 대통령은 제1항과 제2항의 처분 또는 명령을 한 때에는 지체없이 국회에 보고하여 그 승인을 얻어야 한다.

헌법에 의하여 체결·공포된 조약과 일반적으로 승인된 국제법규는 국내법과 같은 효력을 가진다. 훈장등의 영전은 이를 받은 자에게만 효력이 있고, 어떠한 특권도 이에 따르지 아니한다. 모든 국민은 종교의 자유를 가진다. 대통령·국무총리·국무위원·행정각부의 장·헌법재판소 재판관·법관·중앙선거관리위원회 위원·감사원장·감사위원 기타 법률이 정한 공무원이 그 직무집행에 있어서 헌법이나 법률을 위배한 때에는 국회는 탄핵의 소추를 의결할 수 있다.

모든 국민은 신체의 자유를 가진다. 누구든지 법률에 의하지 아니하고는 체포·구속·압수·수색 또는 심문을 받지 아니하며, 법률과 적법한 절차에 의하지 아니하고는 처벌·보안처분 또는 강제노역을 받지 아니한다. 모든 국민은 인간으로서의 존엄과 가치를 가지며, 행복을 추구할 권리를 가진다. 국가는 개인이 가지는 불가침의 기본적 인권을 확인하고 이를 보장할 의무를 진다.

제1항의 탄핵소추는 국회재적의원 3분의 1 이상의 발의가 있어야 하며, 그 의결은 국회재적의원 과반수의 찬성이 있어야 한다. 다만, 대통령에 대한 탄핵소추는 국회재적의원 과반수의 발의와 국회재적의원 3분의 2 이상의 찬성이 있어야 한다. 국회의원과 정부는 법률안을 제출할 수 있다. 모든 국민은 언론·출판의 자유와 집회·결사의 자유를 가진다.

환경권의 내용과 행사에 관하여는 법률로 정한다. 국가는 여자의 복지와 권익의 향상을 위하여 노력하여야 한다. 재판의 전심절차로서 행정심판을 할 수 있다. 행정심판의 절차는 법률로 정하되, 사법절차가 준용되어야 한다. 누구든지 체포 또는 구속을 당한 때에는 적부의 심사를 법원에 청구할 권리를 가진다.
                                """.trimIndent(),
                                    style = MaterialTheme.typography.body1.copy(
                                        lineHeight = 25.sp,
                                        letterSpacing = 1.sp
                                    )
                                )
                            }
                        }
                    }
                }

                AnimatedVisibility(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    visible = !pagerState.isScrollInProgress && pagerState.currentPage == 0,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {

                    val infiniteTransition = rememberInfiniteTransition()

                    val alpha by infiniteTransition.animateFloat(
                        initialValue = 0.0f,
                        targetValue = 0.3f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        )
                    )

                    val move by infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(
                                2000,
                                easing = FastOutSlowInEasing
                            ),
                            repeatMode = RepeatMode.Restart
                        )
                    )

                    Column {
                        Icon(
                            modifier = Modifier.alpha(alpha).padding(20.dp).graphicsLayer {
                                translationX = move * -20.dp.value
                            },
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height((56 * 2).dp))
                        Spacer(modifier = Modifier.navigationBarsPadding())
                    }
                }
            }
        }

    }
}