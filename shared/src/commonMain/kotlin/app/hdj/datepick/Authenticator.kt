package app.hdj.datepick

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class Authenticator(
    private val coroutineScope: CoroutineScope /* Use viewModelScope in Android */
) {

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: Flow<FirebaseUser?> = _user

    init {
        Firebase.auth.authStateChanged.onEach {
            _user.value = it
        }.launchIn(coroutineScope)
    }

    fun signIn(credential: AuthCredential) = coroutineScope.launch {
        Firebase.auth.signInWithCredential(credential)
    }

    fun signOut() = coroutineScope.launch {
        Firebase.auth.signOut()
    }

    /*
    * Use in iOS
    * */
    fun close() {
        coroutineScope.cancel()
    }

}