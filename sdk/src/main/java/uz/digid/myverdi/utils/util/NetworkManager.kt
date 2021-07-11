package uz.digid.myverdi.utils.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest


class NetworkManager : ConnectivityManager.NetworkCallback() {

    companion object {
        val instance = NetworkManager()
    }

    val networkStateEvent = SingleLiveEvent<Void?>()

    private var connectivityManager: ConnectivityManager? = null
    private var networkRequest: NetworkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .build()

    fun register(context: Context) {
        connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager?.registerNetworkCallback(networkRequest, this)
    }

    fun unregister() {
        try {
            connectivityManager?.unregisterNetworkCallback(this)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        networkStateEvent.postValue(null)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        networkStateEvent.postValue(null)
    }
}