package app.hdj.datepick.domain.model.featured

interface Featured {

    val id : Long
    val title : String
    val subtitle : String
    val content : String
    val imageUrl : String
    val isPinned : Boolean

}