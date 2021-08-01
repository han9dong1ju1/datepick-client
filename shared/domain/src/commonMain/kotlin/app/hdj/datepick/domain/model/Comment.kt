package app.hdj.datepick.domain.model

interface Comment {
    val text : String
    val timestamp : String
    val author : User
}

data class ParentComment(
    val id : String,
    override val text: String,
    override val timestamp: String,
    override val author: User,
    val replies : List<Reply>
) : Comment

data class Reply(
    val id : String,
    val parentId : String,
    override val text: String,
    override val timestamp: String,
    override val author: User
) : Comment