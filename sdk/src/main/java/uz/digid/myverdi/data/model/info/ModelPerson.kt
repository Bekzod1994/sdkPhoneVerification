package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ModelPerson() : Parcelable {
    @SerializedName("RequestGuid")
    var requestGuid: String? = null

    @SerializedName("Inn")
    var inn: String? = null

    @SerializedName("Pinpp")
    var pinpp: String? = null

    @SerializedName("SurnameC")
    var surnameC: String? = null

    @SerializedName("NameC")
    var nameC: String? = null

    @SerializedName("PatronymC")
    var patronymC: String? = null

    @SerializedName("SurnameL")
    var surnameL: String? = null

    @SerializedName("NameL")
    var nameL: String? = null

    @SerializedName("PatronymL")
    var patronymL: String? = null

    @SerializedName("SurnameE")
    var surnameE: String? = null

    @SerializedName("NameE")
    var nameE: String? = null

    @SerializedName("BirthDate")
    var birthDate: String? = null

    @SerializedName("Sex")
    var sex: String? = null

    @SerializedName("SexName")
    var sexName: String? = null

    @SerializedName("SexNameUz")
    var sexNameUz: String? = null

    @SerializedName("BirthCountry")
    var birthCountry: String? = null

    @SerializedName("BirthCountryName")
    var birthCountryName: String? = null

    @SerializedName("BirthCountryNameUz")
    var birthCountryNameUz: String? = null

    @SerializedName("BirthPlace")
    var birthPlace: String? = null

    @SerializedName("Nationality")
    var nationality: String? = null

    @SerializedName("NationalityName")
    var nationalityName: String? = null

    @SerializedName("NationalityNameUz")
    var nationalityNameUz: String? = null

    @SerializedName("DocumentType")
    var documentType: String? = null

    @SerializedName("DocumentTypeName")
    var documentTypeName: String? = null

    @SerializedName("DocumentTypeNameUz")
    var documentTypeUz: String? = null

    @SerializedName("DocumentSerialNumber")
    var documentSerialNumber: String? = null

    @SerializedName("DocumentDateIssue")
    var documentDateIssue: String? = null

    @SerializedName("DocumentDateValid")
    var documentDateValid: String? = null

    @SerializedName("DocumentIssuedBy")
    var documentIssuedBy: String? = null

    @SerializedName("PersonStatus")
    var personStatus = 0

    @SerializedName("PersonStatusValue")
    var personStatusValue: String? = null

    @SerializedName("Citizenship")
    var citizenship: String? = null

    @SerializedName("CitizenshipName")
    var citizenshipName: String? = null

    @SerializedName("CitizenshipNameUz")
    var citizenshipNameUz: String? = null

    @SerializedName("Additional")
    var additional: String? = null

    constructor(parcel: Parcel) : this() {
        requestGuid = parcel.readString()
        inn = parcel.readString()
        pinpp = parcel.readString()
        surnameC = parcel.readString()
        nameC = parcel.readString()
        patronymC = parcel.readString()
        surnameL = parcel.readString()
        nameL = parcel.readString()
        patronymL = parcel.readString()
        surnameE = parcel.readString()
        nameE = parcel.readString()
        birthDate = parcel.readString()
        sex = parcel.readString()
        sexName = parcel.readString()
        sexNameUz = parcel.readString()
        birthCountry = parcel.readString()
        birthCountryName = parcel.readString()
        birthCountryNameUz = parcel.readString()
        birthPlace = parcel.readString()
        nationality = parcel.readString()
        nationalityName = parcel.readString()
        nationalityNameUz = parcel.readString()
        documentType = parcel.readString()
        documentTypeName = parcel.readString()
        documentTypeUz = parcel.readString()
        documentSerialNumber = parcel.readString()
        documentDateIssue = parcel.readString()
        documentDateValid = parcel.readString()
        documentIssuedBy = parcel.readString()
        personStatus = parcel.readInt()
        personStatusValue = parcel.readString()
        citizenship = parcel.readString()
        citizenshipName = parcel.readString()
        citizenshipNameUz = parcel.readString()
        additional = parcel.readString()
    }

    override fun toString(): String {
        return "ModelPerson{" +
                "pinpp='" + pinpp + '\'' +
                ", surnameC='" + surnameC + '\'' +
                ", nameC='" + nameC + '\'' +
                ", patronymC='" + patronymC + '\'' +
                ", surnameL='" + surnameL + '\'' +
                ", nameL='" + nameL + '\'' +
                ", patronymL='" + patronymL + '\'' +
                ", surnameE='" + surnameE + '\'' +
                ", nameE='" + nameE + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", sex='" + sex + '\'' +
                ", sexName='" + sexName + '\'' +
                ", sexNameUz='" + sexNameUz + '\'' +
                ", birthCountry='" + birthCountry + '\'' +
                ", birthCountryName='" + birthCountryName + '\'' +
                ", birthCountryNameUz='" + birthCountryNameUz + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", nationality='" + nationality + '\'' +
                ", nationalityName='" + nationalityName + '\'' +
                ", nationalityNameUz='" + nationalityNameUz + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentTypeName='" + documentTypeName + '\'' +
                ", documentTypeUz='" + documentTypeUz + '\'' +
                ", documentSerialNumber='" + documentSerialNumber + '\'' +
                ", documentDateIssue='" + documentDateIssue + '\'' +
                ", documentDateValid='" + documentDateValid + '\'' +
                ", documentIssuedBy='" + documentIssuedBy + '\'' +
                ", personStatus=" + personStatus +
                ", personStatusValue='" + personStatusValue + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", citizenshipName='" + citizenshipName + '\'' +
                ", citizenshipNameUz='" + citizenshipNameUz + '\'' +
                ", additional='" + additional + '\'' +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(requestGuid)
        parcel.writeString(inn)
        parcel.writeString(pinpp)
        parcel.writeString(surnameC)
        parcel.writeString(nameC)
        parcel.writeString(patronymC)
        parcel.writeString(surnameL)
        parcel.writeString(nameL)
        parcel.writeString(patronymL)
        parcel.writeString(surnameE)
        parcel.writeString(nameE)
        parcel.writeString(birthDate)
        parcel.writeString(sex)
        parcel.writeString(sexName)
        parcel.writeString(sexNameUz)
        parcel.writeString(birthCountry)
        parcel.writeString(birthCountryName)
        parcel.writeString(birthCountryNameUz)
        parcel.writeString(birthPlace)
        parcel.writeString(nationality)
        parcel.writeString(nationalityName)
        parcel.writeString(nationalityNameUz)
        parcel.writeString(documentType)
        parcel.writeString(documentTypeName)
        parcel.writeString(documentTypeUz)
        parcel.writeString(documentSerialNumber)
        parcel.writeString(documentDateIssue)
        parcel.writeString(documentDateValid)
        parcel.writeString(documentIssuedBy)
        parcel.writeInt(personStatus)
        parcel.writeString(personStatusValue)
        parcel.writeString(citizenship)
        parcel.writeString(citizenshipName)
        parcel.writeString(citizenshipNameUz)
        parcel.writeString(additional)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelPerson> {
        override fun createFromParcel(parcel: Parcel): ModelPerson {
            return ModelPerson(parcel)
        }

        override fun newArray(size: Int): Array<ModelPerson?> {
            return arrayOfNulls(size)
        }
    }
}