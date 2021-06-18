package app.hdj.shared.firebase

import dev.gitlive.firebase.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.IllegalStateException

actual class FirebaseStorage(val android: com.google.firebase.storage.FirebaseStorage) {

    actual fun reference(): StorageReference =
        StorageReference(android.reference)

    actual fun reference(withPath: String) =
        StorageReference(android.getReference(withPath))

}

actual class StorageReference(val android: com.google.firebase.storage.StorageReference) {

    actual fun child(path: String) =
        StorageReference(android.child(path))

    actual suspend fun delete() {
        android.delete().await()
    }

    actual suspend fun upload(data: FirebaseStorageData) : String? {
        val url = android.putBytes(data).continueWithTask{  task ->
            if (task.isSuccessful) android.downloadUrl
            else throw task.exception ?: IllegalStateException()
        }.await()
        return url?.toString()
    }

}

actual val Firebase.storage: FirebaseStorage
    get() = FirebaseStorage(
        com.google.firebase.storage.FirebaseStorage.getInstance()
    )

actual typealias FirebaseStorageData = ByteArray