package com.kroger.sps.grinder.ui.component

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kroger.mobile.kaf.application.feature.FeatureComponent
import com.kroger.mobile.kaf.context.Component
import com.kroger.mobile.kaf.context.KafContext
import com.kroger.mobile.kaf.envresolver.EnvironmentSettingsComponent
import com.kroger.mobile.kaf.log.LogComponent
import com.kroger.mobile.kaf.preferences.PreferencesComponent
import com.kroger.sps.grinder.ui.R
import com.kroger.sps.grinder.ui.activity.GrinderHomeActivity
import java.util.LinkedList

class GrinderFeatureComponent(private val kafContext: KafContext) : FeatureComponent {

    private val mKafContext: KafContext = this.kafContext

    override fun onCreate() {
        mKafContext.getComponent(LogComponent::class.java).isLoggingEnabled = true
    }

    override fun getTitle() = mKafContext.applicationContext?.getString(R.string.grinder_application_title)

    override fun getComponentDependencies(): MutableList<out Class<out Component>> =
        LinkedList<Class<out Component?>>().apply {
            add(LogComponent::class.java)
            add(PreferencesComponent::class.java)
            add(EnvironmentSettingsComponent::class.java)
        }

    override fun launch(activityContext: Context?, extras: Bundle?) {
        activityContext?.startActivity(Intent(activityContext, GrinderHomeActivity()::class.java))
    }

    override fun getOrder() = 0
    override fun isVisible() = true
    override fun isEnabled() = true
    override fun getCustomView() = null
}