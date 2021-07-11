package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ModelPersonPassport(
    @SerializedName("Name")
    var name: String? = null,

    @SerializedName("Surname")
    var surname: String? = null,

    @SerializedName("BirthDate")
    var birthDate: String? = null,

    @SerializedName("Nationality")
    var nationality: String? = null,

    @SerializedName("Sex")
    var sex: String? = null,

    @SerializedName("ExpiryDate")
    var expiryDate: String? = null,

    @SerializedName("DocumentNumber")
    var documentNumber: String? = null,

    @SerializedName("DocumentType")
    var documentType: String? = null,

    @SerializedName("Issuer")
    var issuer: String? = null,

    @SerializedName("Pinpp")
    var pinpp: String? = null,

    @SerializedName("Additional")
    var additional: String? = null,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(birthDate)
        parcel.writeString(nationality)
        parcel.writeString(sex)
        parcel.writeString(expiryDate)
        parcel.writeString(documentNumber)
        parcel.writeString(documentType)
        parcel.writeString(issuer)
        parcel.writeString(pinpp)
        parcel.writeString(additional)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPersonPassport> {
        override fun createFromParcel(parcel: Parcel): ModelPersonPassport {
            return ModelPersonPassport(parcel)
        }

        override fun newArray(size: Int): Array<ModelPersonPassport?> {
            return arrayOfNulls(size)
        }
    }
}