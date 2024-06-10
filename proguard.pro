-ignorewarnings

-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses

-keepparameternames

-repackageclasses 'g000sha256.material.color_scheme.a'

-keep class kotlin.Metadata

######
######
######

-assumenosideeffects class kotlin.jvm.internal.Intrinsics {

    public static void check*(...);

    public static void throw*(...);

}

######
######
######

-keep @g000sha256.material.color_scheme.annotation.Keep class *

-keepclassmembers class * {

    @g000sha256.material.color_scheme.annotation.Keep *;

}