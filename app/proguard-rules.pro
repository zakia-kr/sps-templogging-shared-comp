# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 1
-dontwarn android.support.**
-dontwarn org.apache.**
-dontwarn com.squareup.picasso.**
-dontnote sun.misc.Unsafe
-dontwarn javax.annotation.**
-dontwarn org.jetbrains.annotations.**
-dontwarn kotlin.**
-dontwarn com.android.mkstubs.**
-dontwarn com.symbol.emdk.simulscan.**
-dontwarn com.kroger.mobile.kaf.**
-dontwarn module-info

-dontwarn org.mockito.**
-dontwarn java.math.**
-keepattributes RuntimeVisibleAnnotations

-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}
-keep class kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoaderImpl** { *; }

-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}

-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

-keepclassmembernames class kotlin.coroutines.SafeContinuation {
    volatile <fields>;
}
-dontwarn kotlinx.coroutines.flow.**inlined**

-dontwarn java.lang.instrument.ClassFileTransformer
-dontwarn sun.misc.SignalHandler
-dontwarn java.lang.instrument.Instrumentation
-dontwarn sun.misc.Signal
-keep class sun.misc.Unsafe { *; }
-dontnote sun.misc.Unsafe

-keepnames class com.worldturner.medeia.**

-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable,!class/unboxing/enum

-keepattributes Signature, InnerClasses, EnclosingMethod

#Default keeps
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep interface * implements com.kroger.mobile.kaf.context.Component { *; }
-keep class * implements com.kroger.mobile.kaf.context.Component { *; }
-keep class * implements com.kroger.mobile.kaf.context.KafContext { *; }
-keep class **.BuildConfig { *; }
-keepnames class * implements com.kroger.mobile.kaf.context.Component { *; }
-keepnames class com.kroger.mobile.kaf.** { *; }
-keepnames interface com.kroger.mobile.kaf.** { *; }
-keep class com.kroger.mobile.sps.brc.ui.viewmodel.** { *; }

#-keepnames class com.kroger.mobile.sps.** { *; }
#-keepnames class com.kroger.sps.** { *; }
# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-keep class okhttp3.** { *;}
-keep class retrofit2.**

# Retrofit2
-dontwarn retrofit2.Platform$Java8
-dontwarn retrofit2.-KotlinExtensions
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

-keep class com.dynatrace.** { *; }

# KAF Libraries
-keep,allowshrinking class com.datalogic.** { *; }
-keep class com.symbol.** { *; }

# SPS-Extras
-keep class com.kroger.sps.mobile.reportfeedback.** { *; }
-keep public class * extends androidx.lifecycle.ViewModel {
    public <init>(com.kroger.mobile.kaf.context.KafContext);
}

# Paho MQTT
-keep class * implements org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory

#Kotlin dependencies
-keep interface org.jetbrains.annotations.** { *; }

##Android method stubber
#-keep class com.android.mkstubs.stubber.MethodStubber { *; }

#KDS
-dontwarn com.kroger.design.**
#Glide
-dontwarn com.bumptech.glide.**

-keep,allowshrinking class com.kroger.** { *; }

#KAT Analytics SDK
-dontwarn org.slf4j.*

-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt

-keep,includedescriptorclasses class com.kroger.sps.templogging.**$$serializer { *; }
-keepclassmembers class com.kroger.sps.templogging.** {
    *** Companion;
}
-keepclasseswithmembers class com.kroger.sps.templogging.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class com.kroger.mobile.sps.temperature.**$$serializer { *; }
-keepclassmembers class com.kroger.mobile.sps.temperature.** {
    *** Companion;
}
-keepclasseswithmembers class com.kroger.mobile.sps.temperature.** {
    kotlinx.serialization.KSerializer serializer(...);
}