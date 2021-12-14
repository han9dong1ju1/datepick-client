package app.hdj.datepick.android.ui.screens.others.diaryDetail

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.glance.Image
import app.hdj.datepick.android.utils.CreateQrCode
import app.hdj.datepick.ui.components.NetworkImage
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun DiaryCoverPageContent() {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NetworkImage(
                url = "https://picsum.photos/200/300",
                modifier = Modifier
                    .size(200.dp, 300.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "안국동 데이트",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "2021. 11. 1",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )


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