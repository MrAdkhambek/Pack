package com.adkhambek.sms

import com.google.android.gms.common.api.CommonStatusCodes

public sealed class SmsRetrievalException(
    message: String,
) : Exception(message) {

    public class SmsTimeoutException :
        SmsRetrievalException("SMS retrieval timed out")

    public class SmsCancelledException :
        SmsRetrievalException("SMS retrieval was cancelled")

    public class SmsErrorException(
        public val statusCode: Int,
    ) : SmsRetrievalException(CommonStatusCodes.getStatusCodeString(statusCode))

    internal companion object {

        fun fromStatusCode(statusCode: Int): SmsRetrievalException = when (statusCode) {
            CommonStatusCodes.TIMEOUT -> SmsTimeoutException()
            CommonStatusCodes.CANCELED -> SmsCancelledException()
            else -> SmsErrorException(statusCode)
        }
    }
}
