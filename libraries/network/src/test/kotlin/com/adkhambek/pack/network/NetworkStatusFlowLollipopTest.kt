@file:Suppress("DEPRECATION")

package com.adkhambek.pack.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowNetwork
import org.robolectric.shadows.ShadowNetworkInfo

@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
@RunWith(RobolectricTestRunner::class)
internal class NetworkStatusFlowLollipopTest {

    @Test
    internal fun `should be connected when connected to WiFi`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network: Network = ShadowNetwork.newInstance(123)
        val networkInfo = ShadowNetworkInfo.newInstance(
            NetworkInfo.DetailedState.CONNECTED,
            ConnectivityManager.TYPE_WIFI,
            0,
            true,
            NetworkInfo.State.CONNECTED
        )

        shadowOf(cm).addNetwork(network, networkInfo)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertTrue(first)
    }

    @Test
    internal fun `should be connected when connected to Network`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network: Network = ShadowNetwork.newInstance(123)
        val networkInfo = ShadowNetworkInfo.newInstance(
            NetworkInfo.DetailedState.CONNECTED,
            ConnectivityManager.TYPE_MOBILE,
            0,
            true,
            NetworkInfo.State.CONNECTED
        )

        shadowOf(cm).addNetwork(network, networkInfo)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertTrue(first)
    }

    @Test
    internal fun `should be not connected when connected to Bluetooth`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network: Network = ShadowNetwork.newInstance(123)
        val networkInfo = ShadowNetworkInfo.newInstance(
            NetworkInfo.DetailedState.CONNECTED,
            ConnectivityManager.TYPE_BLUETOOTH,
            0,
            true,
            NetworkInfo.State.CONNECTED
        )

        shadowOf(cm).clearAllNetworks()
        shadowOf(cm).addNetwork(network, networkInfo)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertFalse(first)
    }

    @Test
    internal fun `should be not connected when connected to Ethernet`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network: Network = ShadowNetwork.newInstance(123)
        val networkInfo = ShadowNetworkInfo.newInstance(
            NetworkInfo.DetailedState.CONNECTED,
            ConnectivityManager.TYPE_ETHERNET,
            0,
            true,
            NetworkInfo.State.CONNECTED
        )

        shadowOf(cm).clearAllNetworks()
        shadowOf(cm).addNetwork(network, networkInfo)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertFalse(first)
    }

    @Test
    internal fun `should be not connected when disconnected`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network: Network = ShadowNetwork.newInstance(123)
        val networkInfo = ShadowNetworkInfo.newInstance(
            NetworkInfo.DetailedState.CONNECTED,
            ConnectivityManager.TYPE_ETHERNET,
            0,
            true,
            NetworkInfo.State.CONNECTED
        )

        shadowOf(cm).addNetwork(network, networkInfo)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertTrue(first)

        shadowOf(cm).removeNetwork(network)
        val second = networkStatusFlow.first()
        Assertions.assertFalse(second)
    }
}
