package app.hdj.shared.firebase

import cocoapods.FirebaseStorage.FIRStorage
import cocoapods.FirebaseStorage.*
import cocoapods.FirebaseStorage.FIRStorageReference
import dev.gitlive.firebase.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSData
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class FirebaseStorage(private val ios: FIRStorage) {

    actual fun reference(): StorageReference =
        StorageReference(ios.reference())

    actual fun reference(withPath: String) =
        StorageReference(ios.referenceWithPath(withPath))

}

actual class StorageReference(private val ios: FIRStorageReference) {

    actual fun child(path: String) =
        StorageReference(ios.child(path))

    actual suspend fun delete() = suspendCancellableCoroutine<Unit> { cont ->
        if (!cont.isActive) return@suspendCancellableCoroutine
        ios.deleteWithCompletion { nsError ->
            if (nsError != null) {
                cont.resumeWithException(Throwable(nsError.localizedDescription()))
            } else {
                cont.resume(Unit)
            }
        }

    }

    actual suspend fun upload(data: FirebaseStorageData) =
        suspendCancellableCoroutine<String?> { cont ->
            if (!cont.isActive) return@suspendCancellableCoroutine
            ios.putData(data, null) { _, uploadError ->
                if (uploadError != null) cont.resumeWithException(Throwable(uploadError.localizedDescription()))
                else ios.downloadURLWithCompletion { url, downloadUrlError ->
                    if (downloadUrlError != null) {
                        cont.resumeWithException(Throwable(downloadUrlError.localizedDescription()))
                    }
                    else {
                        cont.resume(url?.relativeString)
                    }
                }
            }
        }

}

actual val Firebase.storage get() = FirebaseStorage(FIRStorage.storage())

actual typealias FirebaseStorageData = NSData