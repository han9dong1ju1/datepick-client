package app.hdj.datepick.domain.model.course

interface Course {

    val id : Long
    val title : String
    val region : String
    val expectedAt : String
    val pickCount : Long
    val importCount : Long
    val userId : Long
    val thumbnailUrl : String?

}