package me.adkhambek.pack.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import org.robolectric.shadows.ShadowNetworkCapabilities

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
internal class NetworkStatusFlowTest {

    @Test
    internal fun `should be connected when connected to WiFi`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = ShadowNetworkCapabilities.newInstance()
        shadowOf(networkCapabilities).addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        shadowOf(cm).setNetworkCapabilities(cm.activeNetwork, networkCapabilities)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertTrue(first)
    }

    @Test
    internal fun `should be connected when connected to Network`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = ShadowNetworkCapabilities.newInstance()
        shadowOf(networkCapabilities).addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        shadowOf(cm).setNetworkCapabilities(cm.activeNetwork, networkCapabilities)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertTrue(first)
    }

    @Test
    internal fun `should be not connected when connected to Bluetooth`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = ShadowNetworkCapabilities.newInstance()
        shadowOf(networkCapabilities).addTransportType(NetworkCapabilities.TRANSPORT_BLUETOOTH)
        shadowOf(cm).setNetworkCapabilities(cm.activeNetwork, networkCapabilities)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertFalse(first)
    }

    @Test
    internal fun `should be not connected when connected to Ethernet`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = ShadowNetworkCapabilities.newInstance()
        shadowOf(networkCapabilities).addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        shadowOf(cm).setNetworkCapabilities(cm.activeNetwork, networkCapabilities)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertFalse(first)
    }

    @Test
    internal fun `should be connected not when disconnected`() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = ShadowNetworkCapabilities.newInstance()
        shadowOf(networkCapabilities).addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        shadowOf(cm).setNetworkCapabilities(cm.activeNetwork, networkCapabilities)

        val networkStatusFlow = NetworkStatusFlow(cm)
        val first = networkStatusFlow.first()
        Assertions.assertTrue(first)

        shadowOf(networkCapabilities).removeTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        val second = networkStatusFlow.first()
        Assertions.assertFalse(second)
    }
}