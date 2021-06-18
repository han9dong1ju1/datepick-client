package app.hdj.shared.firebase

import dev.gitlive.firebase.firestore.*

expect val CollectionReference.parentReference: DocumentReference?

expect val DocumentReference.parentReference: CollectionReference

expect fun Query.startAt(vararg value: Any?): Query

expect fun Query.startAfter(vararg value: Any?): Query

expect fun Query.startAtDocument(documentSnapshot: DocumentSnapshot?): Query

expect fun Query.startAfterDocument(documentSnapshot: DocumentSnapshot?): Query