package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class ModelMRZ() : Parcelable {
    @SerializedName("Line1")
    var line1: String? = null

    @SerializedName("Line2")
    var line2: String? = null

    @SerializedName("Line3")
    var line3: String? = null

    @SerializedName("Additional")
    var additional: String? = null

    constructor(parcel: Parcel) : this() {
        line1 = parcel.readString()
        line2 = parcel.readString()
        line3 = parcel.readString()
        additional = parcel.readString()
    }

    override fun toString(): String {
        return "ModelMRZ{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", line3='" + line3 + '\'' +
                ", additional='" + additional + '\'' +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(line1)
        parcel.writeString(line2)
        parcel.writeString(line3)
        parcel.writeString(additional)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelMRZ> {
        override fun createFromParcel(parcel: Parcel): ModelMRZ {
            return ModelMRZ(parcel)
        }

        override fun newArray(size: Int): Array<ModelMRZ?> {
            return arrayOfNulls(size)
        }
    }
}