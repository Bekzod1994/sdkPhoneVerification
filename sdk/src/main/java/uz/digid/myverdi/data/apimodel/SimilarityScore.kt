package uz.digid.myverdi.data.apimodel

import com.google.gson.annotations.SerializedName

data class SimilarityScore (
    @SerializedName("code")
    var code : Int,
    @SerializedName("similarity")
    val similarity: Double
)