package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


class ModelAddress() : Parcelable {
    @SerializedName("Kadastr")
    var kadastr: String? = null

    var country: Long = 0

    @SerializedName("CountryICAO")
        var countryICAO: String? = null

    @SerializedName("CountryName")
        var countryName: String? = null

    @SerializedName("CountryNameUz")
    var countryNameUz: String? = null

    @SerializedName("Region")
    var region: Long = 0

    @SerializedName("RegionName")
    var regionName: String? = null

    @SerializedName("RegionNameUz")

    var regionNameUz: String? = null

    @SerializedName("District")
     var district: Long = 0

    @SerializedName("DistrictName")

    var districtName: String? = null

    @SerializedName("DistrictNameUz")
    var districtNameUz: String? = null

    @SerializedName("Address")
    var address: String? = null

    @SerializedName("House")
    var house: String? = null

    @SerializedName("Flat")
     val flat: String? = null

    @SerializedName("Block")
    var block: String? = null

    @SerializedName("LiveFromDate")

    var liveFromDate: String? = null

    @SerializedName("Additional")
       var additional: String? = null

    constructor(parcel: Parcel) : this() {
        kadastr = parcel.readString()
        country = parcel.readLong()
        countryICAO = parcel.readString()
        countryName = parcel.readString()
        countryNameUz = parcel.readString()
        region = parcel.readLong()
        regionName = parcel.readString()
        regionNameUz = parcel.readString()
        district = parcel.readLong()
        districtName = parcel.readString()
        districtNameUz = parcel.readString()
        address = parcel.readString()
        house = parcel.readString()
        block = parcel.readString()
        liveFromDate = parcel.readString()
        additional = parcel.readString()
    }

    override fun toString(): String {
        return "ModelAddress{" +
                "kadastr='" + kadastr + '\'' +
                ", country=" + country +
                ", countryICAO='" + countryICAO + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryNameUz='" + countryNameUz + '\'' +
                ", region=" + region +
                ", regionName='" + regionName + '\'' +
                ", regionNameUz='" + regionNameUz + '\'' +
                ", district=" + district +
                ", districtName='" + districtName + '\'' +
                ", districtNameUz='" + districtNameUz + '\'' +
                ", address='" + address + '\'' +
                ", house='" + house + '\'' +
                ", flat='" + flat + '\'' +
                ", block='" + block + '\'' +
                ", liveFromDate='" + liveFromDate + '\'' +
                ", additional='" + additional + '\'' +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(kadastr)
        parcel.writeLong(country)
        parcel.writeString(countryICAO)
        parcel.writeString(countryName)
        parcel.writeString(countryNameUz)
        parcel.writeLong(region)
        parcel.writeString(regionName)
        parcel.writeString(regionNameUz)
        parcel.writeLong(district)
        parcel.writeString(districtName)
        parcel.writeString(districtNameUz)
        parcel.writeString(address)
        parcel.writeString(house)
        parcel.writeString(block)
        parcel.writeString(liveFromDate)
        parcel.writeString(additional)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelAddress> {
        override fun createFromParcel(parcel: Parcel): ModelAddress {
            return ModelAddress(parcel)
        }

        override fun newArray(size: Int): Array<ModelAddress?> {
            return arrayOfNulls(size)
        }
    }
}