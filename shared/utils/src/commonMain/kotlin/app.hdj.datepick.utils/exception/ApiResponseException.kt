package app.hdj.datepick.utils.exception

open class ApiResponseException(
    val code : Int = -1,
    val responseMessage : String
) : Exception("Code: $code | Message: $responseMessage")