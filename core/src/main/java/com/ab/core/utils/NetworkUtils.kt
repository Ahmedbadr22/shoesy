package com.ab.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.StringRes
import com.ab.core.R
import retrofit2.Response

data class NetworkRequestException(
    @StringRes val messageResId: Int,
    val code: Int = 0
) : Exception()

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

inline fun <reified T> safeApiCall(apiCall: () -> Response<T>): T {
    return try {
        val apiResponse = apiCall.invoke() as Response<*>
        when (apiResponse.code()) {
            in 200..201 -> {
               apiResponse.body() as T
            }
            204 -> {
                throw NetworkRequestException(R.string.no_content_found_please_try_again_later, apiResponse.code())
            }
            400 -> {
                throw NetworkRequestException(R.string.bad_request_try_again, apiResponse.code())
            }
            401 -> {
                throw NetworkRequestException(R.string.user_not_found_please_try_again, apiResponse.code())
            }
            404 -> {
                throw NetworkRequestException(R.string.server_not_found_please_contact_it_for_support, apiResponse.code())
            }
            in 405..499 -> {
                throw NetworkRequestException(R.string.client_error_please_try_again_later, apiResponse.code())
            }
            in 500..599 -> {
                throw NetworkRequestException(R.string.server_error_please_contact_it_for_support, apiResponse.code())
            }
            else -> {
                throw NetworkRequestException(R.string.unknown_error_please_contact_it_support, apiResponse.code())
            }
        }
    } catch (exception: Exception){
        throw (exception as NetworkRequestException).copy(messageResId = R.string.unknown_error_please_contact_it_support, code = 0)
    }
}