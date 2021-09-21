package app.hdj.datepick.android.ui.screens.main.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import app.hdj.datepick.android.ui.components.ProfileImage
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.ui.utils.*

@Composable
fun MenuScreenHeader(me : User?) {
    val navController = LocalAppNavController.current

    Surface(
        onClick = {
            navController.navigateRoute(
                if (me == null) AppNavigationGraph.LoginDialog
                else AppNavigationGraph.UserProfileEdit
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        if (me != null) {
            ConstraintLayout(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)) {

                val (imageRef, nameRef, arrowIconRef) = createRefs()

                ProfileImage(
                    modifier = Modifier
                        .size(50.dp)
                        .constrainAs(imageRef, t2t() + b2b() + s2s()),
                    profileImageUrl = me.profileImageUrl
                )

                Text(
                    modifier = Modifier
                        .constrainAs(
                            nameRef, t2t(imageRef)
                                    + b2b(imageRef)
                                    + s2e(imageRef, margin = 20.dp)
                                    + e2s(arrowIconRef, margin = 20.dp)
                                    + fillWidthToConstraint
                        ),
                    text = me.nickname,
                    style = MaterialTheme.typography.h6
                )

                Icon(
                    modifier = Modifier
                        .constrainAs(
                            arrowIconRef, t2t() + b2b() + e2e()
                        ),
                    imageVector = Icons.Rounded.ChevronRight,
                    contentDescription = null
                )

            }
        } else {

        }
    }
}