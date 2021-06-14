package app.hdj.firebase

import dev.gitlive.firebase.Firebase

expect class FirebaseStorageData

expect val Firebase.storage: FirebaseStorage

expect class FirebaseStorage {

    fun reference(): StorageReference

    fun reference(withPath: String): StorageReference

}

expect class StorageReference {

    fun child(path: String): StorageReference

    suspend fun delete()

    suspend fun upload(data: FirebaseStorageData) : String?

}