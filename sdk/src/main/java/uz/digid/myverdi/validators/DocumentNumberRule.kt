package uz.digid.myverdi.validators

import android.content.Context
import androidx.appcompat.widget.AppCompatEditText

import com.mobsandgeeks.saripaar.QuickRule

import java.util.regex.Pattern

import uz.digid.myverdi.R


/**
 * Created by Surface on 15/08/2017.
 */

class DocumentNumberRule : QuickRule<AppCompatEditText>() {

    override fun isValid(editText: AppCompatEditText): Boolean {
        val text = editText.text!!.toString().replace(" ","")
        val patternDate = Pattern.compile(REGEX)
        val matcherDate = patternDate.matcher(text)
        return matcherDate.find()
    }

    override fun getMessage(context: Context): String {
        return context.getString(R.string.error_validation_document_number)
    }

    companion object {
        private const val REGEX = "(?<Serial>[A-Z<]{2})(?<Number>[0-9]{7})$"
    }
}
