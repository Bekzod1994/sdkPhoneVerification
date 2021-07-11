package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import uz.digid.myverdi.data.model.request.Answere
import uz.digid.myverdi.data.model.response.ClientResponse


class ModelPersonAnswere():Parcelable{
    val answere: Answere? = null

    @SerializedName("DocumentReadingTime")
    val documentReadingTime: Long = 0

    @SerializedName("ServiceAnswereTime")
    val serviceAnswereTime: Long = 0

    @SerializedName("RequestGuid")
    val requestGuid: String? = null

    @SerializedName("ModelServiceInfo")
    val modelServiceInfo: ModelServiceInfo? = null

    @SerializedName("Person")
    var person: ModelPerson? = null

    @SerializedName("Address")
    val address: ModelAddressAnswere? = null

    @SerializedName("AddressTemporary")
    val addressTemporary: ModelAddressTemproaryAnswere? = null

    @SerializedName("Contacts")
    val contacts: ModelContactAnswere? = null

    @SerializedName("Additional")
    val additional: ModelPersonAdditional? = null

    @SerializedName("ModelMRZ")
    val modelMRZ: ModelMRZAnswere? = null

    @SerializedName("ModelPersonPassport")
    var modelPersonPassport: ModelPersonPassportAnswere? = null

    @SerializedName("ModelPersonIdCard")
    val modelPersonIdCard: ModelPersonIdCardAnswere? = null

    @SerializedName("ModelPersonPhoto")
    var modelPersonPhoto: ModelPersonPhoto? = null

    @SerializedName("LivenessAnswere")
    val livenessAnswere: LivenessAnswere? = null

    @SerializedName("SignString")
    val signString: String? = null

    @SerializedName("ClientData")
    val clientData: ClientResponse? = null

    constructor(parcel: Parcel) : this() {
        person = parcel.readParcelable(ModelPerson::class.java.classLoader)
        modelPersonPassport = parcel.readParcelable(ModelPersonPassportAnswere::class.java.classLoader)
        modelPersonPhoto = parcel.readParcelable(ModelPersonPhoto::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(person, flags)
        parcel.writeParcelable(modelPersonPassport, flags)
        parcel.writeParcelable(modelPersonPhoto, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPersonAnswere> {
        override fun createFromParcel(parcel: Parcel): ModelPersonAnswere {
            return ModelPersonAnswere(parcel)
        }

        override fun newArray(size: Int): Array<ModelPersonAnswere?> {
            return arrayOfNulls(size)
        }
    }


}