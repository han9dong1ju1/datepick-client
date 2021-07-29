package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

interface Comment : Id {
    val text: String
    val author: Author
    val timestamp: String
}

@Serializable
data class ParentComment(
    override val id: String,
    override val text: String,
    override val author: Author,
    override val timestamp: String,
    val replies: List<ReplyComment>
) : Comment

@Serializable
data class ReplyComment(
    override val id: String,
    override val text: String,
    override val author: Author,
    override val timestamp: String
) : Comment