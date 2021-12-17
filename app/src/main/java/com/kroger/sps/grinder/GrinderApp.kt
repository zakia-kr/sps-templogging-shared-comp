package com.kroger.sps.grinder

import com.kroger.analytics.definitions.MetaData
import com.kroger.mobile.kaf.application.BaseApplication
import com.kroger.mobile.kaf.application.BuildInfoComponent
import com.kroger.mobile.kaf.application.feature.impl.DefaultFeatureManagerComponent
import com.kroger.mobile.kaf.application.menu.impl.DefaultMenuManagerComponent
import com.kroger.mobile.kaf.auth.AuthComponent
import com.kroger.mobile.kaf.auth.extras.DefaultAuthHandlerComponent
import com.kroger.mobile.kaf.context.ComponentManager
import com.kroger.mobile.kaf.context.KafContext
import com.kroger.mobile.kaf.context.impl.DefaultComponentManager
import com.kroger.mobile.kaf.envresolver.EnvironmentSettingsComponent
import com.kroger.mobile.kaf.envresolver.impl.DefaultSettingsHolderComponent
import com.kroger.mobile.kaf.http.CorrelationIdComponent
import com.kroger.mobile.kaf.http.OkHttpComponent
import com.kroger.mobile.kaf.http.RetrofitComponent
import com.kroger.mobile.kaf.http.ssl.AugmentedSSLComponent
import com.kroger.mobile.kaf.location.DefaultStoreInfoComponent
import com.kroger.mobile.kaf.location.StoreInfoComponent
import com.kroger.mobile.kaf.log.adapter.AndroidLogAdapterComponent
import com.kroger.mobile.kaf.log.adapter.CaptureLogAdapterComponent
import com.kroger.mobile.kaf.log.manager.LogManagerComponent
import com.kroger.mobile.kaf.preferences.PreferencesComponent
import com.kroger.mobile.kaf.preferences.impl.SharedPreferencesComponent
import com.kroger.mobile.kaf.scanner.DefaultScannerComponent
import com.kroger.mobile.kaf.scanner.test.AppiumTestScannerComponent
import com.kroger.sps.grinder.auth.KrogerAppAuthWebViewComponent
import com.kroger.sps.grinder.auth.OkHttpBodyLoggingComponent
import com.kroger.sps.grinder.auth.OktaAppAuthConfigComponent
import com.kroger.sps.grinder.auth.OktaAuthInterceptorComponent
import com.kroger.sps.grinder.tempauth.TempHostnameVerifier
import com.kroger.sps.grinder.ui.component.GrinderFeatureComponent
import com.kroger.sps.mobile.about.AboutComponent
import com.kroger.sps.mobile.analytics.kat.AnalyticsConfigurationComponent
import com.kroger.sps.mobile.analytics.kat.DefaultBehavioralAnalyticsPublisherComponent
import com.kroger.sps.mobile.appproperty.AppPropertyServiceComponent
import com.kroger.sps.mobile.notifications.MQTTOutageNotificationComponent
import com.kroger.sps.mobile.notifications.mqtt.AndroidStoreMQTTComponent
import com.kroger.sps.mobile.ui.util.NetworkLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor

class GrinderApp : BaseApplication() {

    override val componentManager: ComponentManager = DefaultComponentManager(
        kafContext,
        AppiumTestScannerComponent::class,
        CorrelationIdComponent::class,
        LogManagerComponent::class,
        AndroidLogAdapterComponent::class,
        DefaultAuthHandlerComponent::class,
        DefaultFeatureManagerComponent::class,
        DefaultSettingsHolderComponent::class,
        DefaultMenuManagerComponent::class,
        DefaultStoreInfoComponent::class,
        DefaultScannerComponent::class,
        EnvironmentSettingsComponent::class,
        OkHttpBodyLoggingComponent::class,
        SharedPreferencesComponent::class,
        AugmentedSSLComponent::class,
        KrogerAppAuthWebViewComponent::class,
        OktaAppAuthConfigComponent::class,
        OktaAuthInterceptorComponent::class,
        CaptureLogAdapterComponent.RoomCaptureLogAdapterComponent::class,
        RetrofitComponent::class,
        AnalyticsConfigurationComponent::class,
        MQTTOutageNotificationComponent::class,
        DefaultAuthHandlerComponent::class,
        DefaultBehavioralAnalyticsPublisherComponent::class,
        DefaultMenuManagerComponent::class,
        AndroidStoreMQTTComponent::class,
        AboutComponent::class,
        PreferencesComponent::class,
        AppPropertyServiceComponent::class,
        GrinderFeatureComponent::class
    )

    override fun onCreate() {
        super.onCreate()
        NetworkLiveData.init(this)
        kafContext.getComponent(AnalyticsConfigurationComponent::class.java).configure(
            appName = MetaData.AppName.Compliance,
            applicationId = BuildConfig.APPLICATION_ID
        )
        getComponent(AuthComponent::class).revoke()
        // Adding this code for store-no fix.
        getComponent(OkHttpComponent::class).builder
            .hostnameVerifier(TempHostnameVerifier.INSTANCE)
            .addInterceptor { chain: Interceptor.Chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .header(
                            "User-Agent",
                            "StoreProductivitySuite/" + getComponent(BuildInfoComponent::class).applicationVersion.userAgentVersion
                        ).build()
                )
            }.build()
        setStoreLocationInfo()
    }

    override val parentContext: KafContext? = null

    private fun setStoreLocationInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            kafContext.getComponent(StoreInfoComponent::class).currentStoreInfo
        }
    }
}