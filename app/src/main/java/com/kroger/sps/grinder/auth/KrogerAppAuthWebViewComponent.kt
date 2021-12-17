package com.kroger.sps.grinder.auth

import com.auth0.android.jwt.JWT
import com.kroger.mobile.kaf.auth.appauth.webview.AppAuthWebviewComponent
import com.kroger.mobile.kaf.context.KafContext
import com.kroger.mobile.kaf.preferences.PreferencesComponent
import net.openid.appauth.AuthState

class KrogerAppAuthWebViewComponent(kafContext: KafContext?) : AppAuthWebviewComponent(kafContext) {

    companion object{
        private const val PREFS_AUTH_STATE = "PREFS_AUTH_STATE"
    }

    override fun onCreate() {
        super.onCreate()
        kafContext.getComponent(PreferencesComponent::class).remove(PREFS_AUTH_STATE)
    }

    override fun getUsername() = if (mCredentials != null && mCredentials.authState != null && !mCredentials.authState.idToken.isNullOrEmpty())
            JWT(mCredentials.authState.idToken!!).getClaim("euid").asString() else null

    override fun onAppAuthComplete(authState: AuthState?) {
        super.onAppAuthComplete(authState)
        kafContext.getComponent(PreferencesComponent::class).remove(PREFS_AUTH_STATE)
    }

}