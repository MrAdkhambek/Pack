@file:JvmSynthetic

package com.adkhambek.pack.network

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.annotation.TargetApi
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@RequiresPermission(value = ACCESS_NETWORK_STATE)
internal fun networkStatusFlow(connectManager: ConnectivityManager): Flow<Boolean> = callbackFlow {
    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            trySend(true)
        }

        override fun onLost(network: Network) {
            trySend(false)
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        connectManager.registerDefaultNetworkCallback(networkCallback)
    } else {
        val builder = NetworkRequest.Builder()
        connectManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    val isConnected = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        networkStatusM(connectManager)
    } else {
        networkStatus(connectManager)
    }

    send(isConnected)
    awaitClose { connectManager.unregisterNetworkCallback(networkCallback) }
}

@TargetApi(Build.VERSION_CODES.M)
@RequiresPermission(value = ACCESS_NETWORK_STATE)
private fun networkStatusM(connectivityManager: ConnectivityManager): Boolean {
    val network = connectivityManager.activeNetwork ?: return false
    val cap = connectivityManager.getNetworkCapabilities(network) ?: return false
    return cap.hasTransport(TRANSPORT_WIFI) || cap.hasTransport(TRANSPORT_CELLULAR)
}

@Suppress("DEPRECATION") // allNetworks
@RequiresPermission(value = ACCESS_NETWORK_STATE)
private fun networkStatus(connectivityManager: ConnectivityManager): Boolean {
    return connectivityManager.allNetworks.firstOrNull { network ->
        val cap = connectivityManager.getNetworkCapabilities(network) ?: return@firstOrNull false
        return@firstOrNull cap.hasTransport(TRANSPORT_WIFI) || cap.hasTransport(TRANSPORT_CELLULAR)
    } != null
}
