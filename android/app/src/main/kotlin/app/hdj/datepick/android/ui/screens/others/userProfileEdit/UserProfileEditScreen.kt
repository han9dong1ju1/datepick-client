package app.hdj.datepick.android.ui.screens.others.userProfileEdit

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.launch
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.utils.GetPhotoExceptGif
import app.hdj.datepick.domain.model.user.UserGender.*
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import coil.compose.LocalImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.google.accompanist.insets.navigationBarsHeight
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
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

    var profileImage by remember { mutableStateOf<Any?>(me?.profileImage) }

    var nickname by remember { mutableStateOf(me?.nickname.orEmpty()) }

    var gender by remember { mutableStateOf(me?.gender ?: U) }
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
            InsetSmallTopAppBar(
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
                        bitmap?.recycle()

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
                            tonalElevation = 10.dp,
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
                                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
                                )
                            }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                textStyle = LocalTextStyle.current,
                modifier = Modifier.fillMaxWidth(),
                value = nickname,
                onValueChange = { nickname = it },
                colors = TextFieldDefaults.material3TextFieldColors()
            )

            Spacer(modifier = Modifier.height(10.dp))

            HighSurface(modifier = Modifier.fillMaxWidth(), onClick = {
                genderDropdownMenuOpenState = true
            }) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    text = when (gender) {
                        U -> "알리지 않음"
                        M -> "남자"
                        F -> "여자"
                    }
                )

                DropdownMenu(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                    expanded = genderDropdownMenuOpenState, onDismissRequest = {
                        genderDropdownMenuOpenState = false
                    }) {
                    DropdownMenuItem(onClick = {
                        gender = M
                        genderDropdownMenuOpenState = false
                    }) { Text(text = "남자") }
                    DropdownMenuItem(onClick = {
                        gender = F
                        genderDropdownMenuOpenState = false
                    }) { Text(text = "여자") }
                    DropdownMenuItem(onClick = {
                        gender = U
                        genderDropdownMenuOpenState = false
                    }) { Text(text = "알리지 않음") }
                }
            }

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