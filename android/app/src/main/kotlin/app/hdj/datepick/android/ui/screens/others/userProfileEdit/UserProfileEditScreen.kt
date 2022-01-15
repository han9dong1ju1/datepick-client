package app.hdj.datepick.android.ui.screens.others.userProfileEdit

import android.graphics.Bitmap
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.utils.GetPhotoExceptGif
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.NetworkImage
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import coil.compose.LocalImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.google.accompanist.insets.navigationBarsHeight
import io.ktor.utils.io.core.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@Composable
fun UserProfileEditScreen(vm: UserProfileEditViewModelDelegate = hiltViewModel<UserProfileEditViewModel>()) {

    val context = LocalContext.current
    val navController = LocalAppNavController.current
    val lifecycle = LocalLifecycleOwner.current
    val imageLoader = LocalImageLoader.current

    val (state, effect, event) = vm.extract()

    val me = LocalMe.current

    val scrollState = rememberScrollState()

    var profileImage by remember { mutableStateOf<Any?>(me?.imageUrl) }

    var nickname by remember { mutableStateOf(me?.nickname.orEmpty()) }

    var gender by remember { mutableStateOf(me?.gender) }
    var genderDropdownMenuOpenState by remember { mutableStateOf(false) }

    val photoPicker = rememberLauncherForActivityResult(GetPhotoExceptGif()) {
        profileImage = it
    }

    effect.collectInLaunchedEffect {
        when (it) {
            UserProfileEditViewModelDelegate.Effect.UnregisterCompleted -> {
                navController.popBackStack()
                navController.navigate(AppNavigationGraph.Main.route)
            }
            UserProfileEditViewModelDelegate.Effect.ProfileUpdateCompleted -> {
                navController.popBackStack()
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()

    BaseScaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { TopAppBarBackButton() },
                title = {

                }
            )
        },
        bottomBar = {
            Column(modifier = Modifier.padding(20.dp)) {

                BaseButton(modifier = Modifier.fillMaxWidth(), text = "탈퇴하기", onClick = {
                    event(UserProfileEditViewModelDelegate.Event.Unregister("좆같아서"))
                })

                Spacer(modifier = Modifier.height(20.dp))

                BaseButton(modifier = Modifier.fillMaxWidth(), text = "변경하기", onClick = {

                    val coilImageCompress = ImageRequest.Builder(context)
                        .data(profileImage)
                        .lifecycle(lifecycle)
                        .build()

                    coroutineScope.launch {
                        val bitmap = imageLoader.execute(coilImageCompress).drawable?.toBitmap()

                        val byteArrayOutputStream = ByteArrayOutputStream()
                        val compressFormat =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) Bitmap.CompressFormat.WEBP_LOSSY
                            else Bitmap.CompressFormat.WEBP

                        bitmap?.compress(compressFormat, 10, byteArrayOutputStream)
                        val byteArray = byteArrayOutputStream.toByteArray()

                        event(
                            UserProfileEditViewModelDelegate.Event.UpdateProfile(
                                nickname,
                                gender,
                                buildPacket { writeFully(byteArray) }
                            )
                        )
                    }
                })

                Spacer(modifier = Modifier.navigationBarsHeight())

            }
        }
    ) {

        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {

            Box(modifier = Modifier
                .size(100.dp)
                .clickable { photoPicker.launch() }
            ) {
                NetworkImage(
                    modifier = Modifier.fillMaxSize(),
                    url = profileImage,
                    imageRequestBuilder = { transformations(CircleCropTransformation()) },
                    onFailed = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            shape = CircleShape
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(20.dp)
                                        .align(Alignment.Center),
                                    imageVector = Icons.Rounded.Person,
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface)
                                )
                            }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = nickname,
                onValueChange = { nickname = it }
            )

            Spacer(modifier = Modifier.height(10.dp))

        }

    }

}

@Preview
@Composable
fun UserProfileEditScreenPreview() {
    PreviewScope {
        UserProfileEditScreen(fakeUserProfileEditViewModel())
    }
}