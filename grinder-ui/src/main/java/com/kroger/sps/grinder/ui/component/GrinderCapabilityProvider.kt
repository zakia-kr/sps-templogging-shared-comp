package com.kroger.sps.grinder.ui.component

import com.kroger.mobile.kaf.application.CapabilityProvider
import com.kroger.mobile.kaf.context.Component

class GrinderCapabilityProvider : CapabilityProvider() {

    override fun getComponentDependencies(): List<Class<out Component?>?> {
        return listOf(GrinderFeatureComponent::class.java)
    }
}
