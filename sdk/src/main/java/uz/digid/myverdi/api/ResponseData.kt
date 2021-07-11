package uz.digid.myverdi.api

data class ResponseData<T>(
    val code: Int,
    val message: String = "Successful",
    val response: T? = null
)