package uz.digid.myverdi.data.model.info

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ServiceInfo() : Parcelable {
    @SerializedName("ScannerSerial")
    var scannerSerial: String? = null

    @SerializedName("ProductNumber")
    var productNumber: String? = null

    @SerializedName("Version")
    var version: String? = null

    @SerializedName("FWVersion")
    private var fWVersion: String? = null

    @SerializedName("OCRVersion")
    var ocrVersion: String? = null

    @SerializedName("ClientIP")
    var clientIP: String? = null

    @SerializedName("ClientMAC")
    var clientMAC: String? = null

    @SerializedName("ClientOS")
    var clientOS: String? = null

    @SerializedName("ApplicationVersion")
    var applicationVersion: String? = null

    @SerializedName("Additional")
    var additional: String? = null

    constructor(parcel: Parcel) : this() {
        scannerSerial = parcel.readString()
        productNumber = parcel.readString()
        version = parcel.readString()
        fWVersion = parcel.readString()
        ocrVersion = parcel.readString()
        clientIP = parcel.readString()
        clientMAC = parcel.readString()
        clientOS = parcel.readString()
        applicationVersion = parcel.readString()
        additional = parcel.readString()
    }


    override fun toString(): String {
        return "ServiceInfo{" +
                "scannerSerial='" + scannerSerial + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", version='" + version + '\'' +
                ", fWVersion='" + fWVersion + '\'' +
                ", ocrVersion='" + ocrVersion + '\'' +
                ", clientIP='" + clientIP + '\'' +
                ", clientMAC='" + clientMAC + '\'' +
                ", clientOS='" + clientOS + '\'' +
                ", applicationVersion='" + applicationVersion + '\'' +
                ", additional='" + additional + '\'' +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(scannerSerial)
        parcel.writeString(productNumber)
        parcel.writeString(version)
        parcel.writeString(fWVersion)
        parcel.writeString(ocrVersion)
        parcel.writeString(clientIP)
        parcel.writeString(clientMAC)
        parcel.writeString(clientOS)
        parcel.writeString(applicationVersion)
        parcel.writeString(additional)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ServiceInfo> {
        override fun createFromParcel(parcel: Parcel): ServiceInfo {
            return ServiceInfo(parcel)
        }

        override fun newArray(size: Int): Array<ServiceInfo?> {
            return arrayOfNulls(size)
        }
    }
}