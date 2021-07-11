package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.model.request.Answere

class ModelPersonIdCardAnswere() : Parcelable {
    private var answere: Answere? = null

    @SerializedName("PersonIdCard")
     var personIdCard: ModelPersonIdCard? = null

    constructor(parcel: Parcel) : this() {
        personIdCard = parcel.readParcelable(ModelPersonIdCard::class.java.classLoader)
    }

    override fun toString(): String {
        return "ModelPersonIdCardAnswere{" +
                "answere=" + answere +
                ", personIdCard=" + personIdCard +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(personIdCard, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPersonIdCardAnswere> {
        override fun createFromParcel(parcel: Parcel): ModelPersonIdCardAnswere {
            return ModelPersonIdCardAnswere(parcel)
        }

        override fun newArray(size: Int): Array<ModelPersonIdCardAnswere?> {
            return arrayOfNulls(size)
        }
    }
}