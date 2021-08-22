package app.hdj.datepick.domain.model.place

interface Place {
    val id : Long
    val kakaoId : Long
    val name : String
    val category : Category
    val address : String
    val latitude : Double
    val longitude : Double
    val rating : Double
    val isPicked : Boolean
    val photos : List<String>

    interface Category {
        val type : String
        val subtype : String

        val typeAsString get() = """$type ㆍ $subtype"""
    }
}