package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.model.request.Answere

class ModelAddressAnswere() : Parcelable {
     var answere: Answere? = null

    @SerializedName("Address")
     var modelAddress: ModelAddress? = null

    constructor(parcel: Parcel) : this() {
        modelAddress = parcel.readParcelable(ModelAddress::class.java.classLoader)
    }

    override fun toString(): String {
        return "ModelAddressAnswere{" +
                "answere=" + answere +
                ", modelAddress=" + modelAddress +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(modelAddress, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelAddressAnswere> {
        override fun createFromParcel(parcel: Parcel): ModelAddressAnswere {
            return ModelAddressAnswere(parcel)
        }

        override fun newArray(size: Int): Array<ModelAddressAnswere?> {
            return arrayOfNulls(size)
        }
    }
}