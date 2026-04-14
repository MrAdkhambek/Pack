package com.adkhambek.sms

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Looper
import androidx.test.core.app.ApplicationProvider
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.auth.api.phone.SmsRetrieverClient
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.android.gms.tasks.Task
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
internal class SmsFlowTest {

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()

        mockkStatic(SmsRetriever::class)
        val mockClient = mockk<SmsRetrieverClient>()
        val mockTask = mockk<Task<Void>>()
        every { SmsRetriever.getClient(any<Context>()) } returns mockClient
        every { mockClient.startSmsRetriever() } returns mockTask
        every { mockTask.addOnFailureListener(any()) } returns mockTask
    }

    @After
    fun tearDown() {
        unmockkStatic(SmsRetriever::class)
    }

    @Test
    fun `SUCCESS broadcast emits the message`() = runTest {
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        val flow = smsFlow(context, intentFilter)

        val deferred = async(UnconfinedTestDispatcher(testScheduler)) {
            flow.first()
        }

        sendSmsBroadcast(CommonStatusCodes.SUCCESS, "Your OTP is 123456")
        shadowOf(Looper.getMainLooper()).idle()

        assertEquals("Your OTP is 123456", deferred.await())
    }

    @Test
    fun `non-SUCCESS broadcast closes flow with exception`() = runTest {
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        val flow = smsFlow(context, intentFilter)

        val deferred = async(UnconfinedTestDispatcher(testScheduler)) {
            runCatching { flow.first() }
        }

        sendSmsBroadcast(CommonStatusCodes.TIMEOUT)
        shadowOf(Looper.getMainLooper()).idle()

        val result = deferred.await()
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is RuntimeException)
    }

    @Test
    fun `transform function is applied correctly`() = runTest {
        val smsFlow = SmsFlow(context) { msg -> msg.filter { it.isDigit() } }

        val deferred = async(UnconfinedTestDispatcher(testScheduler)) {
            smsFlow.first()
        }

        sendSmsBroadcast(CommonStatusCodes.SUCCESS, "Your OTP is 123456")
        shadowOf(Looper.getMainLooper()).idle()

        assertEquals("123456", deferred.await())
    }

    @Test
    fun `BroadcastReceiver is unregistered on flow cancellation`() = runTest {
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        val mockContext = mockk<Context>(relaxed = true)
        val flow = smsFlow(mockContext, intentFilter)

        val job = async(UnconfinedTestDispatcher(testScheduler)) {
            flow.collect { }
        }

        job.cancelAndJoin()

        verify(exactly = 1) { mockContext.unregisterReceiver(any()) }
    }

    private fun sendSmsBroadcast(statusCode: Int, message: String? = null) {
        val intent = Intent(SmsRetriever.SMS_RETRIEVED_ACTION).apply {
            putExtra(SmsRetriever.EXTRA_STATUS, Status(statusCode))
            if (message != null) {
                putExtra(SmsRetriever.EXTRA_SMS_MESSAGE, message)
            }
        }
        context.sendBroadcast(intent)
    }
}
