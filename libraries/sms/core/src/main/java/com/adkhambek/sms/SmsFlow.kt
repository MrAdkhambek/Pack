package com.adkhambek.sms

import android.content.Context
import android.content.IntentFilter
import com.google.android.gms.auth.api.phone.SmsRetriever
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class SmsFlow private constructor(
    delegate: Flow<String>,
) : Flow<String> by delegate {

    public companion object {

        @JvmStatic
        @JvmOverloads
        public operator fun invoke(
            context: Context,
            transform: suspend (value: String) -> String = { it },
        ): SmsFlow {
            val intentFilter = IntentFilter()
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION)

            val delegate = smsFlow(
                context = context,
                intentFilter = intentFilter
            ).map(transform)

            return SmsFlow(delegate)
        }
    }
}
