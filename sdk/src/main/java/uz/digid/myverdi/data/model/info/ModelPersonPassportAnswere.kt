package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.model.request.Answere

class ModelPersonPassportAnswere() : Parcelable {
    private var answere: Answere? = null

    @SerializedName("PersonPassport")
     var personPassport: ModelPersonPassport? = null

    constructor(parcel: Parcel) : this() {
        personPassport = parcel.readParcelable(ModelPersonPassport::class.java.classLoader)
    }

    override fun toString(): String {
        return "ModelPersonPassportAnswere{" +
                "answere=" + answere +
                ", personPassport=" + personPassport +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(personPassport, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPersonPassportAnswere> {
        override fun createFromParcel(parcel: Parcel): ModelPersonPassportAnswere {
            return ModelPersonPassportAnswere(parcel)
        }

        override fun newArray(size: Int): Array<ModelPersonPassportAnswere?> {
            return arrayOfNulls(size)
        }
    }
}