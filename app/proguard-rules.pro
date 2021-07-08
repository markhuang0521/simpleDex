# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#### OkHttp, Retrofit and Moshi
# Retain generic type information for use by reflection by converters     and adapters.
-dontwarn okhttp3.**
-dontwarn retrofit2.**
-dontwarn javax.annotation.**
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keep @com.squareup.moshi.JsonQualifier interface *
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

-keepclassmembers class * {
    @com.squareup.moshi.FromJson <methods>;
    @com.squareup.moshi.ToJson <methods>;
}



-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}

-keepnames @kotlin.Metadata class com.mingDev.cleanpokedex.database.entity**
-keep class com.mingDev.cleanpokedex.network.responses** { *; }
-keepclassmembers class com.mingDev.cleanpokedex.network.responses.** { *; }






# When editing this file, update the following files as well:
# - META-INF/com.android.tools/proguard/kotlin-reflect.pro
# - META-INF/com.android.tools/r8-from-1.6.0/kotlin-reflect.pro
# - META-INF/com.android.tools/r8-upto-1.6.0/kotlin-reflect.pro
# Keep Metadata annotations so they can be parsed at runtime.
-keep class kotlin.Metadata { *; }

# Keep implementations of service loaded interfaces
# R8 will automatically handle these these in 1.6+
-keep interface kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader
-keep class * implements kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader { public protected *; }
-keep interface kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
-keep class * implements kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition { public protected *; }

# Keep generic signatures and annotations at runtime.
# R8 requires InnerClasses and EnclosingMethod if you keepattributes Signature.
-keepattributes InnerClasses,Signature,RuntimeVisible*Annotations,EnclosingMethod

# Don't note on API calls from different JVM versions as they're gated properly at runtime.
-dontnote kotlin.internal.PlatformImplementationsKt

# Don't note on internal APIs, as there is some class relocating that shrinkers may unnecessarily find suspicious.
-dontwarn kotlin.reflect.jvm.internal.**