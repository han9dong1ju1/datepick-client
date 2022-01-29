package app.hdj.datepick.android.ui.screens.featured

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopBar
import app.hdj.datepick.ui.components.TopAppBarBackButton

@Composable
fun FeaturedDetailScreen(featuredId: Long?) {

    BaseScaffold(
        topBar = {
            BaseTopBar(
                navigationIcon = {
                    TopAppBarBackButton(contentColor = Color.White)
                },
                title = {

                },
                actions = {
                    IconButton(
                        onClick = {

                        },
                        content = {
                            Icon(
                                imageVector = Icons.Rounded.Share,
                                null,
                                tint = Color.White
                            )
                        }
                    )
                }
            )
        }
    ) {

        Column(modifier = Modifier.padding(it)) {



        }

    }

}