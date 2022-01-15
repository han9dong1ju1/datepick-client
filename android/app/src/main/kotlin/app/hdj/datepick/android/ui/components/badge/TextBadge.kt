package app.hdj.datepick.android.ui.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object DatePickBadges {

    val new = @Composable {
        TextBadge(text = "NEW", color = Color(0xFFFF557D))
    }

    val update = @Composable {
        TextBadge(text = "업데이트", color = Color(0xFF66CE41))
    }

    val event = @Composable {
        TextBadge(text = "이벤트", color = Color(0xFFFFBE1C))
    }

    val notice = @Composable {
        TextBadge(text = "공지", color = Color(0xFF3E95FF))
    }

}

@Composable
fun TextBadge(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.2f), RoundedCornerShape(100.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text, color = color,
            style = MaterialTheme.typography.caption
        )
    }
}