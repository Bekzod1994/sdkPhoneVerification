package uz.digid.myverdi.utils.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import java.net.InetAddress
import java.net.NetworkInterface
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


object AppUtil {

    fun showAlertDialog(
        activity: Activity?,
        title: String?,
        message: String?,
        buttonText: String?,
        isCancelable: Boolean,
        listener: DialogInterface.OnClickListener?
    ) {
        showAlertDialog(activity, title, message, buttonText, null, null, isCancelable, listener)
    }

    @SuppressLint("ResourceAsColor")
    private fun showAlertDialog(
        activity: Activity?,
        title: String?,
        message: String?,
        positiveButtonText: String?,
        negativeButtonText: String?,
        neutralButtonText: String?,
        isCancelable: Boolean,
        listener: DialogInterface.OnClickListener?
    ) {
        val dialogBuilder = AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(isCancelable)

        if (!positiveButtonText.isNullOrEmpty()) dialogBuilder.setPositiveButton(
            positiveButtonText,
            listener
        )
        if (!negativeButtonText.isNullOrEmpty()) dialogBuilder.setNegativeButton(
            negativeButtonText,
            listener
        )
        if (!neutralButtonText.isNullOrEmpty()) dialogBuilder.setNeutralButton(
            neutralButtonText,
            listener
        )
       val dialog =  dialogBuilder.show()
        val positiveButton: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        val positiveButtonLL = positiveButton.layoutParams as LinearLayout.LayoutParams
        positiveButtonLL.gravity = Gravity.CENTER
        positiveButton.layoutParams = positiveButtonLL
    }

    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    val Float.dp: Float
        get() = (this / Resources.getSystem().displayMetrics.density)
    val Float.px: Float
        get() = (this * Resources.getSystem().displayMetrics.density)
    fun getIPAddress(useIPv4: Boolean): String {
        try {
            val interfaces: List<NetworkInterface> = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                val addrs: List<InetAddress> = Collections.list(intf.inetAddresses)
                for (addr in addrs) {
                    if (!addr.isLoopbackAddress) {
                        val sAddr: String = addr.hostAddress
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        val isIPv4 = sAddr.indexOf(':') < 0
                        if (useIPv4) {
                            if (isIPv4) return sAddr
                        } else {
                            if (!isIPv4) {
                                val delim = sAddr.indexOf('%') // drop ip6 zone suffix
                                return if (delim < 0) sAddr.toUpperCase(Locale.ROOT) else sAddr.substring(
                                    0,
                                    delim
                                ).toUpperCase(
                                    Locale.ROOT
                                )
                            }
                        }
                    }
                }
            }
        } catch (ignored: Exception) {
        } // for now eat exceptions
        return ""
    }
    fun md5(s: String): String {
        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            val digest: MessageDigest = MessageDigest.getInstance(MD5)
            digest.update(s.toByteArray())
            val messageDigest: ByteArray = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
    fun showKeyboard(view: View): Boolean {
        try {
            val inputManager: InputMethodManager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            return inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return false
    }

}