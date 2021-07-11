package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.model.request.Answere

class ModelPhoto() : Parcelable {
    @SerializedName("Answere")
     var answere: Answere? = null

    @SerializedName("PersonPhoto")
    var personPhoto: String? = null

    @SerializedName("Additional")
    var additional: String? = null

    constructor(parcel: Parcel) : this() {
        personPhoto = parcel.readString()
        additional = parcel.readString()
    }

    override fun toString(): String {
        return "ModelPhoto{" +
                "answere=" + answere +
                ", personPhoto='" + personPhoto + '\'' +
                ", additional='" + additional + '\'' +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(personPhoto)
        parcel.writeString(additional)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPhoto> {
        override fun createFromParcel(parcel: Parcel): ModelPhoto {
            return ModelPhoto(parcel)
        }

        override fun newArray(size: Int): Array<ModelPhoto?> {
            return arrayOfNulls(size)
        }
    }
}