package me.adkhambek.pack.network

import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.flow.Flow

public class NetworkStatusFlow private constructor(
    delegate: Flow<Boolean>
) : Flow<Boolean> by delegate {

    public companion object {
        @[JvmStatic RequiresPermission(value = ACCESS_NETWORK_STATE)]
        public operator fun invoke(context: Context): NetworkStatusFlow {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return invoke(cm)
        }

        @[JvmStatic RequiresPermission(value = ACCESS_NETWORK_STATE)]
        public operator fun invoke(connectivityManager: ConnectivityManager): NetworkStatusFlow {
            return invoke(networkStatusFlow(connectivityManager))
        }

        @[JvmStatic RequiresPermission(value = ACCESS_NETWORK_STATE)]
        private operator fun invoke(delegate: Flow<Boolean>): NetworkStatusFlow = NetworkStatusFlow(delegate)
    }
}