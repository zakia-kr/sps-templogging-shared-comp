package com.kroger.sps.grinder.auth

import com.kroger.mobile.kaf.auth.http.AuthInterceptorComponent
import com.kroger.mobile.kaf.context.KafContext
import okhttp3.Request

class OktaAuthInterceptorComponent(kafContext: KafContext?) : AuthInterceptorComponent(kafContext) {

    companion object{
        private const val HEADER_X_AUTH_PROVIDER = "X-Token-Type"
        private const val HEADER_OKTA = "okta"
    }

    override fun addAuthHeaders(requestBuilder: Request.Builder?): Request.Builder? =
        super.addAuthHeaders(requestBuilder).addHeader(HEADER_X_AUTH_PROVIDER, HEADER_OKTA)

}