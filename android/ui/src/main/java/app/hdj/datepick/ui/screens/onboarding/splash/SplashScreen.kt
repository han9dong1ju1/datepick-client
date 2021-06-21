package app.hdj.datepick.ui.screens.onboarding.splash

import android.animation.ObjectAnimator
import android.media.Image
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.os.BuildCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun SplashScreen(vm: SplashViewModelDelegate = hiltViewModel<SplashViewModel>()) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current as AppCompatActivity

    val isLoading = true

    if (BuildCompat.isAtLeastS()) {
        LaunchedEffect(true) {
            context.splashScreen.setOnExitAnimationListener { splashScreenView ->
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = FastOutSlowInInterpolator()
                slideUp.duration = 250L
                slideUp.doOnEnd { splashScreenView.remove() }
                slideUp.start()
            }

            val content: View = context.findViewById(android.R.id.content)
            content.viewTreeObserver.addOnPreDrawListener(object :
                ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean = if (isLoading) false
                else {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    true
                }
            })
        }
    } else {

        if (isLoading) {

            Box(modifier = Modifier.fillMaxSize()) {

                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(100.dp),
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                )

            }

        }

    }

}


@Composable
@Preview
fun SplashScreenPreview() {
    DatePickTheme {
        SplashScreen(fakeSplashViewModel())
    }
}