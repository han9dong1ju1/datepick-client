package app.hdj.shared.firebase

import dev.gitlive.firebase.firestore.*

actual val CollectionReference.parentReference: DocumentReference?
    get() = ios.parent?.let { DocumentReference(it) }

actual val DocumentReference.parentReference: CollectionReference
    get() = CollectionReference(ios.parent)

actual fun Query.startAt(vararg value: Any?): Query =
    Query(ios.queryStartingAtValues(value.toList()))

actual fun Query.startAfter(vararg value: Any?): Query =
    Query(ios.queryStartingAfterValues(value.toList()))

actual fun Query.startAtDocument(documentSnapshot: DocumentSnapshot?): Query =
    documentSnapshot?.let { Query(ios.queryStartingAtDocument(it.ios)) } ?: this

actual fun Query.startAfterDocument(documentSnapshot: DocumentSnapshot?): Query =
    documentSnapshot?.let { Query(ios.queryStartingAfterDocument(it.ios)) } ?: this