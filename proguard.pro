-repackageclasses 'g000sha256.material.color_scheme.a'

-keepparameternames

-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses

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

-keepclassmembers @g000sha256.material.color_scheme.annotation.Keep class * {

    public static final * INSTANCE;

}

-keepclassmembers class * {

    @g000sha256.material.color_scheme.annotation.Keep *;

    *$default(...);

}