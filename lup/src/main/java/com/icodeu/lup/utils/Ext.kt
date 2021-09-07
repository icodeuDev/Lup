package com.icodeu.lup.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Environment
import com.icodeu.lup.LubFactory
import com.icodeu.lup.repo.ErrorData
import com.icodeu.lup.ui.LupMainActivity
import java.util.*
import kotlin.system.exitProcess


fun Context.restartApp(delay: Int = 1, destination: Class<*>? = null) {

    val restartIntent = if (destination == null) {
        this.packageManager
            .getLaunchIntentForPackage(this.packageName)
    } else {
        Intent(this, destination)
    }

    val intent = this.getPendingIntent(restartIntent!!)

    val manager: AlarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    manager[AlarmManager.RTC, System.currentTimeMillis() + delay] = intent
    exitProcess(2)
}


internal fun Context.intentTo(destination: Class<*>, data: ErrorData, reportUrl: String?) {
    val intent = Intent(this, destination)
    intent.putExtra(LupMainActivity.INTENT_ERROR_KEY, data)
    intent.putExtra(LupMainActivity.INTENT_URI_KEY, reportUrl)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
}

internal fun Context.getPendingIntent(destination: Intent): PendingIntent {
    return PendingIntent.getActivity(
        this, 0,
        destination, PendingIntent.FLAG_CANCEL_CURRENT
    )
}

// original code of function below can be found here: https://github.com/simon-heinen/SimpleUi/blob/master/SimpleUI/srcAndroid/simpleui/util/DeviceInformation.java#L56
fun Context.getInfosAboutDevice(): String {
    var s = ""
    try {
        val pInfo = this.packageManager.getPackageInfo(
            this.packageName, PackageManager.GET_META_DATA
        )
        s += """
APP Package Name: ${this.packageName}"""
        s += """
APP Version Name: ${pInfo.versionName}"""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            s += """
APP Version Code: ${pInfo.longVersionCode}"""
        } else {
            s += """
APP Version Code: ${pInfo.versionCode}"""
        }
        s += "\n"
    } catch (e: PackageManager.NameNotFoundException) {
    }
    s += """
OS Version: ${System.getProperty("os.version")} (${Build.VERSION.INCREMENTAL})"""
    s += """
OS API Level: ${Build.VERSION.SDK}"""
    s += """
Device: ${Build.DEVICE}"""
    s += """
Model (and Product): ${Build.MODEL} (${Build.PRODUCT})"""

    // more from
    // http://developer.android.com/reference/android/os/Build.html :
    s += """
Manufacturer: ${Build.MANUFACTURER}"""
    s += """
Other TAGS: ${Build.TAGS}"""
//    s += ("\n screenWidth: "
//            + this.createDisplayContext(Display()))
//    s += ("\n screenHeigth: "
//            + this.window.windowManager.defaultDisplay
//        .height)
    s += """
Keyboard available: ${this.resources.configuration.keyboard != Configuration.KEYBOARD_NOKEYS}"""
    s += """
Trackball available: ${this.resources.configuration.navigation == Configuration.NAVIGATION_TRACKBALL}"""
    s += """
SD Card state: ${Environment.getExternalStorageState()}"""
    val p: Properties = System.getProperties()
    val keys: Enumeration<Any>? = p.keys()
    var key = ""
    while (keys?.hasMoreElements() == true) {
        key = keys.nextElement().toString()
        s += """
> $key = ${p.get(key)}"""
    }
    return s
}

fun Context.copyToClipboard(text:String){
    LubFactory.getInstance().copyToCipboard(context = this,text)
}