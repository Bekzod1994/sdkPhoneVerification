/*
package uz.digid.myverdi.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import uz.digid.myverdi.R
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.nfc.IntentListener

class AppCompatActivityMainActivity : AppCompatActivity() {
    var isShouldClearState: Boolean = false
    var onIntentListener: IntentListener? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injection.providerDataRepository(this)
        setContentView(R.layout.activity_main)
        setResult(Activity.RESULT_CANCELED)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (isShouldClearState)
            outState.clear()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        onIntentListener?.onNewIntent(intent)
    }

    companion object {
        const val REQUESRT_CODE_PHONE_NUMBER = 100
        var nfcErrorCount = 0
        var serialScannerNumber: String? = null
        var publicKey: String? = null
    }
}*/
