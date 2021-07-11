package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ModelPersonIdCard() : Parcelable {
    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Surname")
    var surname: String? = null

    @SerializedName("BirthDate")
    var birthDate: String? = null

    @SerializedName("ExpiryDate")
    var expiryDate: String? = null

    @SerializedName("IssuingDate")
    var issuingDate: String? = null

    @SerializedName("Issuer")
    var issuer: String? = null

    @SerializedName("IssuingCountry")
    var issuingCountry: String? = null

    @SerializedName("DocumentNumber")
    var documentNumber: String? = null

    @SerializedName("Additional")
    var additional: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        surname = parcel.readString()
        birthDate = parcel.readString()
        expiryDate = parcel.readString()
        issuingDate = parcel.readString()
        issuer = parcel.readString()
        issuingCountry = parcel.readString()
        documentNumber = parcel.readString()
        additional = parcel.readString()
    }

    override fun toString(): String {
        return "ModelPersonIdCard{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", issuingDate='" + issuingDate + '\'' +
                ", issuer='" + issuer + '\'' +
                ", issuingCountry='" + issuingCountry + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", additional='" + additional + '\'' +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(birthDate)
        parcel.writeString(expiryDate)
        parcel.writeString(issuingDate)
        parcel.writeString(issuer)
        parcel.writeString(issuingCountry)
        parcel.writeString(documentNumber)
        parcel.writeString(additional)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPersonIdCard> {
        override fun createFromParcel(parcel: Parcel): ModelPersonIdCard {
            return ModelPersonIdCard(parcel)
        }

        override fun newArray(size: Int): Array<ModelPersonIdCard?> {
            return arrayOfNulls(size)
        }
    }
}