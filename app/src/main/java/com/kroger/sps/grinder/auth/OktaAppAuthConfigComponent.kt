package com.kroger.sps.grinder.auth

import android.net.Uri
import com.kroger.mobile.kaf.auth.appauth.AbstractAppAuthConfigurationComponent
import com.kroger.mobile.kaf.context.Component
import com.kroger.mobile.kaf.context.KafContext
import com.kroger.mobile.kaf.envresolver.EnvironmentSettingsComponent
import net.openid.appauth.ClientAuthentication
import net.openid.appauth.NoClientAuthentication
import net.openid.appauth.ResponseTypeValues

class OktaAppAuthConfigComponent(kafContext: KafContext?) :
    AbstractAppAuthConfigurationComponent(kafContext) {

    private var mAuthorizationEndpoint: Uri? = null
    private var mTokenEndpoint: Uri? = null
    private var mRegistrationEndpoint: Uri? = null
    private var mRedirectUrl: Uri? = null
    private var mClientId: String? = null

    override fun getAuthorizationEndpoint(): Uri {
        if (mAuthorizationEndpoint == null) {
            mAuthorizationEndpoint = Uri.parse(
                kafContext.getComponent(EnvironmentSettingsComponent::class)
                    .getStringValue("auth", "authorizationEndpoint")
            )
        }
        return mAuthorizationEndpoint!!
    }

    override fun getTokenEndpoint(): Uri {
        if (mTokenEndpoint == null) {
            mTokenEndpoint = Uri.parse(
                kafContext.getComponent(EnvironmentSettingsComponent::class)
                    .getStringValue("auth", "tokenEndpoint")
            )
        }
        return mTokenEndpoint!!
    }

    override fun getRegistrationEndpoint(): Uri? {
        if (mRegistrationEndpoint == null) {
            val registrationEndpoint =
                kafContext.getComponent(EnvironmentSettingsComponent::class)
                    .getStringValue("auth", "registrationEndpoint")
            mRegistrationEndpoint = if (registrationEndpoint == null) {
                NO_REGISTRATION
            } else {
                Uri.parse(registrationEndpoint)
            }
        }
        return if (mRegistrationEndpoint === NO_REGISTRATION) {
            null
        } else mRegistrationEndpoint
    }

    override fun getRedirectUri(): Uri {
        if (mRedirectUrl == null) {
            mRedirectUrl = Uri.parse(
                kafContext.getComponent(EnvironmentSettingsComponent::class)
                    .getStringValue("auth", "redirectUri")
            )
        }
        return mRedirectUrl!!
    }

    override fun getClientId(): String {
        if (mClientId == null) {
            mClientId = kafContext.getComponent(EnvironmentSettingsComponent::class)
                .getStringValue("auth", "clientId")
        }
        return mClientId!!
    }

    override fun getScopes(): String {
        return SCOPES
    }

    override fun getResponseType(): String {
        return RESPONSE_TYPE
    }

    override fun getClientAuthentication(): ClientAuthentication {
        return NoClientAuthentication.INSTANCE
    }

    override fun getComponentDependencies(): List<Class<out Component?>?> {
        return listOf(EnvironmentSettingsComponent::class.java)
    }

    companion object {
        private val NO_REGISTRATION = Uri.Builder().build()
        private const val SCOPES =
            "openid euid offline_access urn:com:kroger:krogerco:instock:read urn:com:kroger:krogerco:instock:write"
        private const val RESPONSE_TYPE = ResponseTypeValues.CODE
    }
}
