package app.hdj.shared.firebase

import dev.gitlive.firebase.firestore.*

actual val CollectionReference.parentReference: DocumentReference?
    get() = android.parent?.let { DocumentReference(it) }

actual val DocumentReference.parentReference: CollectionReference
    get() = CollectionReference(android.parent)

actual fun Query.startAt(vararg value: Any?): Query =
    Query(android.startAt(value.toList()))

actual fun Query.startAfter(vararg value: Any?): Query =
    Query(android.startAfter(value.toList()))

actual fun Query.startAtDocument(documentSnapshot: DocumentSnapshot?): Query =
    documentSnapshot?.let { Query(android.startAt(it.android)) } ?: this

actual fun Query.startAfterDocument(documentSnapshot: DocumentSnapshot?): Query =
    documentSnapshot?.let { Query(android.startAfter(it.android)) } ?: this