package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.components.DatePickScrollableTabRow
import app.hdj.datepick.ui.components.tabIndicatorOffset

@Composable
fun HomeCategoryTab(
    categories: List<String>,
    onTabSelected : (String) -> Unit
) {

    var selectedCategoryIndex by remember { mutableStateOf(0) }

    categories.firstOrNull()?.let { onTabSelected(it) }

    DatePickScrollableTabRow(
        edgePadding = 20.dp,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        selectedTabIndex = selectedCategoryIndex,
        indicator = { tabPositions ->
            Box(
                Modifier.tabIndicatorOffset(tabPositions[selectedCategoryIndex])
                    .fillMaxWidth()
                    .height(3.dp)
                    .clip(RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
                    .background(color = MaterialTheme.colors.secondary)
            )
        }
    ) {
        categories.forEachIndexed { index, categoryName ->
            Tab(
                modifier = Modifier.height(56.dp),
                selected = selectedCategoryIndex == index,
                onClick = {
                    selectedCategoryIndex = index
                    onTabSelected(categoryName)
                },
                text = {
                    Text(text = categoryName)
                }
            )
        }
    }
}