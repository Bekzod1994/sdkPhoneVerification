package uz.digid.myverdi.utils.extensions

import android.content.Context

fun Context.getStringResourceByName(stringName: String): String? {
    val resId = resources.getIdentifier(stringName, "string", packageName)
    if (resId == 0)
        return ""
    return getString(resId)
}