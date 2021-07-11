package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.apimodel.LivenessScore
import uz.digid.myverdi.data.apimodel.SimilarityScore

data class ValidateResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("livenessScore")
    val livenessScore: LivenessScore,
    @SerializedName(value = "similarityScore", alternate = ["SimilarityScore"])
    val similarityScore: SimilarityScore? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        TODO("livenessScore"),
        TODO("similarityScore")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(code)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ValidateResponse> {
        override fun createFromParcel(parcel: Parcel): ValidateResponse {
            return ValidateResponse(parcel)
        }

        override fun newArray(size: Int): Array<ValidateResponse?> {
            return arrayOfNulls(size)
        }
    }
}