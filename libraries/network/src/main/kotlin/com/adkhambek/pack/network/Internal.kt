@file:JvmSynthetic

package com.adkhambek.pack.network

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_VPN
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

@RequiresPermission(value = ACCESS_NETWORK_STATE)
internal fun networkStatusFlow(connectManager: ConnectivityManager): Flow<Boolean> = callbackFlow {
    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            trySend(true)
        }

        override fun onLost(network: Network) {
            trySend(false)
        }

        override fun onUnavailable() {
            trySend(false)
        }
    }

    val isConnected = currentNetworkStatus(connectManager)
    send(isConnected)

    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
            connectManager.registerDefaultNetworkCallback(networkCallback)
        }

        else -> {
            val request = NetworkRequest.Builder().addCapability(NET_CAPABILITY_INTERNET).build()
            connectManager.registerNetworkCallback(request, networkCallback)
        }
    }
    awaitClose { connectManager.unregisterNetworkCallback(networkCallback) }
}.distinctUntilChanged()

@RequiresPermission(value = ACCESS_NETWORK_STATE)
private fun currentNetworkStatus(connectivityManager: ConnectivityManager): Boolean {
    val network = connectivityManager.activeNetwork ?: return false
    val cap = connectivityManager.getNetworkCapabilities(network) ?: return false

    return cap.hasTransport(TRANSPORT_WIFI) ||
            cap.hasTransport(TRANSPORT_CELLULAR) ||
            cap.hasTransport(TRANSPORT_ETHERNET) ||
            cap.hasTransport(TRANSPORT_VPN)
}
