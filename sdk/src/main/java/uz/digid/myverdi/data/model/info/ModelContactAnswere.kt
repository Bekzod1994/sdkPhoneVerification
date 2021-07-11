package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.model.request.Answere


class ModelContactAnswere() : Parcelable {
     var answere: Answere? = null

    @SerializedName("Contacts")
     var contacts: List<ModelContact>? = null

    constructor(parcel: Parcel) : this() {
        contacts = parcel.createTypedArrayList(ModelContact)
    }

    override fun toString(): String {
        return "ModelContactAnswere{" +
                "answere=" + answere +
                ", contacts=" + contacts +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(contacts)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelContactAnswere> {
        override fun createFromParcel(parcel: Parcel): ModelContactAnswere {
            return ModelContactAnswere(parcel)
        }

        override fun newArray(size: Int): Array<ModelContactAnswere?> {
            return arrayOfNulls(size)
        }
    }
}