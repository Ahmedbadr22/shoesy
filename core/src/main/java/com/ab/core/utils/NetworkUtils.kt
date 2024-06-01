package com.ab.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

enum class InternetSourceEnum {
    Wifi,
    MobileData,
    Ethernet,
    NoInternet
}

object NetworkUtils {

    fun getInternetSource(context: Context): InternetSourceEnum {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities?.run {
            return when {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> InternetSourceEnum.MobileData
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> InternetSourceEnum.Wifi
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> InternetSourceEnum.Ethernet
                else -> InternetSourceEnum.NoInternet
            }
        }
        return InternetSourceEnum.NoInternet
    }

    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
