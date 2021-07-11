package uz.digid.myverdi.ui.nfc

import android.content.Intent

interface IntentListener {
    fun onNewIntent(intent:Intent?)
}