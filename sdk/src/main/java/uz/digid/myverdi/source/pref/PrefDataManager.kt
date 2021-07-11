package uz.digid.myverdi.source.pref

import android.content.Context
import androidx.preference.PreferenceManager

class PrefDataManager(context: Context) : PrefDataRepository {

    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {

        const val PUBLIC_KEY = "public_key"
        val SERIAL_NUMBER = "serial_number"
        val USER_PHONE_NUMBER = "user_phone_number"
        val USER_EMAIL = "user_email"
    }

    override fun setUserPhoneNumber(number: String): String {
         prefs.edit().putString(USER_PHONE_NUMBER, number).apply()
        return getUserPhoneNumber()
    }

    override fun setPubKey(key: String): String {
         prefs.edit().putString(PUBLIC_KEY, key).apply()
        return getPubKey()
    }

    override fun setSerialNumber(serialNumber: String): String {
         prefs.edit().putString(SERIAL_NUMBER, serialNumber).apply()
        return getSerialNumber()
    }

    override fun setUserEmail(email: String): String {
         prefs.edit().putString(USER_EMAIL, email).apply()
        return getUserEmail()
    }

    override fun getPubKey(): String {
        return prefs.getString(PUBLIC_KEY, "") ?: ""
    }

    override fun getSerialNumber(): String {
        return prefs.getString(SERIAL_NUMBER, "") ?: ""
    }

    override fun getUserPhoneNumber(): String {
        return prefs.getString(USER_PHONE_NUMBER, "") ?: ""
    }

    override fun getUserEmail(): String {
        return prefs.getString(USER_EMAIL, "") ?: ""
    }
}