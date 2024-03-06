package com.demo.codeassignment.common


import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetInfo @Inject constructor(private val connectivityManager: ConnectivityManager) {

    @RequiresApi(Build.VERSION_CODES.M)
    fun isInternetOn(): Boolean {
        val result: Boolean
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || activeNetwork.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR
            ) -> true
            else -> false
        }
        return result
    }
}