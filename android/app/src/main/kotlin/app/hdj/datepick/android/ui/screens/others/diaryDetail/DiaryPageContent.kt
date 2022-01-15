package app.hdj.datepick.android.ui.screens.others.diaryDetail

import android.graphics.Bitmap
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.utils.CreateQrCode
import app.hdj.datepick.ui.components.NetworkImage
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.delay

@Composable
fun DiaryCoverPageContent() {

    var keyFrame1 by remember { mutableStateOf(false) }
    var keyFrame2 by remember { mutableStateOf(false) }
    var keyFrame3 by remember { mutableStateOf(false) }
    var keyFrame4 by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        delay(100)
        keyFrame1 = true
        delay(250)
        keyFrame2 = true
        delay(250)
        keyFrame3 = true
        delay(250)
        keyFrame4 = true
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier.height(500.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                AnimateDiaryCoverItem(keyFrame1) {
                    Text(
                        text = "2021. 11. 1",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondary,
                        fontFamily = FontFamily(Font(app.hdj.datepick.ui.R.font.nanummyeongjo))
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "데이트 회고록",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary.copy(0.5f),
                        fontFamily = FontFamily(Font(app.hdj.datepick.ui.R.font.nanummyeongjo))
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }

            item {
                AnimateDiaryCoverItem(
                    keyFrame2,
                    additional = scaleIn(initialScale = 0.5f, animationSpec = tween(durationMillis = 1000))
                ) {
                    NetworkImage(
                        url = "https://picsum.photos/200/300",
                        modifier = Modifier
                            .size(200.dp, 300.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                }
            }

            item {
                AnimateDiaryCoverItem(keyFrame3) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "안국동 데이트",
                        fontFamily = FontFamily(Font(app.hdj.datepick.ui.R.font.nanummyeongjo)),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }


            item {
                AnimateDiaryCoverItem(keyFrame4) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "박현기 지음",
                        fontFamily = FontFamily(Font(app.hdj.datepick.ui.R.font.nanummyeongjo)),
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onBackground.copy(0.5f)
                    )
                }
            }

        }
    }

}

@Composable
private fun LazyItemScope.AnimateDiaryCoverItem(
    show: Boolean,
    additional: EnterTransition = EnterTransition.None,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = show,
        enter = fadeIn(tween(durationMillis = 2000)) + slideInVertically(tween(durationMillis = 1000)) { it - 10 } + additional
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
fun DiaryPageContent() {
    Box(Modifier.fillMaxSize()) {

    }
}

@Composable
fun DiaryLastPageContent() {
    Box(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        var qrCode by remember { mutableStateOf<Bitmap?>(null) }

        CreateQrCode("https://sample.com", IntOffset(100, 100)) {
            qrCode = it
        }

        if (qrCode != null) {

            androidx.compose.foundation.Image(
                modifier = Modifier
                    .padding(20.dp)
                    .size(100.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .align(Alignment.BottomEnd),
                bitmap = qrCode!!.asImageBitmap(),
                contentDescription = null
            )

        }
    }
}