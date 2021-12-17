package com.kroger.sps.grinder.auth

import com.kroger.mobile.kaf.context.KafContext
import com.kroger.mobile.kaf.http.OkHttpLoggingComponent
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpBodyLoggingComponent(kafContext: KafContext?) : OkHttpLoggingComponent(kafContext) {
    override fun getDefaultLogLevel() = HttpLoggingInterceptor.Level.BODY
}