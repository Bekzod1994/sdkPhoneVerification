package uz.digid.myverdi.utils.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 5/18/18
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates
 */
object NetworkUtils {
     fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}