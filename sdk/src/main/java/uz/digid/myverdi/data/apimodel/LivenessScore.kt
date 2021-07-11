package uz.digid.myverdi.data.apimodel

import com.google.gson.annotations.SerializedName

data class LivenessScore (
    @SerializedName("code")
    val code : Int,
    @SerializedName("liveness")
    val liveness: Double
)