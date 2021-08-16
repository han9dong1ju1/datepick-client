package app.hdj.datepick.domain.model.place

interface Place {
    val id : Long
    val kakaoId : Long
    val name : String
    val address : String
    val latitude : Double
    val longitude : Double
    val rating : Double
    val isPicked : Boolean
    val photos : List<String>
}