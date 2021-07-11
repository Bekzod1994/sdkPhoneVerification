package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class ModelContact():Parcelable {
    @SerializedName("ZipCode")
    val zipCode: String? = null

    @SerializedName("Phone")
    val phone: String? = null

    @SerializedName("MobilePhone")
    val mobilePhone: String? = null

    @SerializedName("Fax")
    val fax: String? = null

    @SerializedName("Email")
    val email: String? = null

    @SerializedName("Web")
    val web: String? = null

    @SerializedName("Additional")
    val additional: String? = null

    constructor(parcel: Parcel) : this() {
    }

    override fun toString(): String {
        return "ModelContact{" +
                "zipCode='" + zipCode + '\'' +
                ", phone='" + phone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", web='" + web + '\'' +
                ", additional='" + additional + '\'' +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelContact> {
        override fun createFromParcel(parcel: Parcel): ModelContact {
            return ModelContact(parcel)
        }

        override fun newArray(size: Int): Array<ModelContact?> {
            return arrayOfNulls(size)
        }
    }


}