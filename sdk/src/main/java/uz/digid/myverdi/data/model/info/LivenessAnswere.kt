package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.model.request.Answere


class LivenessAnswere() : Parcelable {

    @SerializedName("answere")
    private val answere: Answere? = null
    @SerializedName(value = "validateResponse",alternate = ["ValidateResponse"])
    val validateResponse: ValidateResponse? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LivenessAnswere> {
        override fun createFromParcel(parcel: Parcel): LivenessAnswere {
            return LivenessAnswere(parcel)
        }

        override fun newArray(size: Int): Array<LivenessAnswere?> {
            return arrayOfNulls(size)
        }
    }

}