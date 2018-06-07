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

#==================================API=================================================
#API里边的类，最好都要避免混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.**
-keep public class com.android.vending.licensing.ILicensingService

-dontwarn android.annotation
-keepattributes *Annotation*

#=====================保留了所有的Native变量名及类名=====================
-keepclasseswithmembernames class * {
    native <methods>;
}
#某些构造方法不能去混淆
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
#枚举类不能去混淆
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#aidl文件不能去混淆.
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

#=====================不混淆资源类=====================
-keepclassmembers class **.R$* {
    public static <fields>;
}
#过滤R文件的混淆：
-keep class **.R$* {
    *;
}

#=================================当前项目==================================================
#一般model最好避免混淆（model无关紧要，不混淆也没多大关系）如：
#-keep class com.why.project.helloworld.bean.**{*;}

#===================================其他常规================================================
#加上这句话，不然会在控制台中报warning警告【不添加的话比较好，可以用来验证签名是否成功】
#-ignorewarnings
#设置混淆的压缩比率 0 ~ 7
-optimizationpasses 5
#Aa aA
-dontusemixedcaseclassnames
#混淆后生产映射文件 map 类名->转化后类名的映射
-verbose
#混淆采用的算法.
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*