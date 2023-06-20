@file:Suppress("FunctionName")
@file:JvmSynthetic

package com.adkhambek.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.auth.api.phone.SmsRetrieverClient
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

internal fun smsFlow(
    context: Context,
    intentFilter: IntentFilter,
): Flow<String> = callbackFlow {
    val broadcast = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val extras = intent?.extras ?: return

            // This is method is deprecated but has issue
            // so we use this method
            @Suppress("DEPRECATION")
            val status = extras.getParcelable<Status>(SmsRetriever.EXTRA_STATUS) ?: return

            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val message = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE) ?: return
                    trySend(message)
                }

                else -> close(RuntimeException(CommonStatusCodes.getStatusCodeString(status.statusCode)))
            }
        }
    }

    val client: SmsRetrieverClient = SmsRetriever.getClient(context)
    val task: Task<Void> = client.startSmsRetriever()
    task.addOnFailureListener(::close)

    context.registerReceiver(broadcast, intentFilter)
    awaitClose { context.unregisterReceiver(broadcast) }
}