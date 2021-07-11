package uz.digid.myverdi.source.pref

interface PrefDataRepository {

    fun setUserPhoneNumber(number: String) :String
    fun setPubKey (key: String):String
    fun setSerialNumber(serialNumber: String) :String
    fun setUserEmail(email: String):String
    fun getUserPhoneNumber():String
    fun getPubKey():String
    fun getSerialNumber() :String
    fun getUserEmail() :String
}