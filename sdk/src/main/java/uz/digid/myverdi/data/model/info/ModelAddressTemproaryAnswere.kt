package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.model.request.Answere


class ModelAddressTemproaryAnswere() : Parcelable {
     var answere: Answere? = null

    @SerializedName("AddressTemproary")
     var addressTemproary: List<ModelAddressTemproary>? = null

    constructor(parcel: Parcel) : this() {
        addressTemproary = parcel.createTypedArrayList(ModelAddressTemproary)
    }


    override fun toString(): String {
        return "ModelAddressTemproaryAnswere{" +
                "answere=" + answere +
                ", addressTemproary=" + addressTemproary +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(addressTemproary)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelAddressTemproaryAnswere> {
        override fun createFromParcel(parcel: Parcel): ModelAddressTemproaryAnswere {
            return ModelAddressTemproaryAnswere(parcel)
        }

        override fun newArray(size: Int): Array<ModelAddressTemproaryAnswere?> {
            return arrayOfNulls(size)
        }
    }
}