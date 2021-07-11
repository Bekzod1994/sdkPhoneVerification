package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.model.request.Answere

class ModelServiceInfo() : Parcelable {
    @SerializedName("Answere")
    var answere: Answere? = null

    @SerializedName("ServiceInfo")
    var serviceInfo: ServiceInfo? = null

    constructor(parcel: Parcel) : this() {

    }


    override fun toString(): String {
        return "ModelServiceInfo{" +
                "answere=" + answere +
                ", serviceInfo=" + serviceInfo +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelServiceInfo> {
        override fun createFromParcel(parcel: Parcel): ModelServiceInfo {
            return ModelServiceInfo(parcel)
        }

        override fun newArray(size: Int): Array<ModelServiceInfo?> {
            return arrayOfNulls(size)
        }
    }
}