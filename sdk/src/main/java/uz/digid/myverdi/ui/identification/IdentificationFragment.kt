/*
package uz.digid.myverdi.ui.identification

import android.Manifest
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.widget.PopupMenuCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import uz.digid.myverdi.R
import uz.digid.myverdi.data.model.DocType
import uz.digid.myverdi.data.model.PersonDetails
import uz.digid.myverdi.data.model.request.*
import uz.digid.myverdi.databinding.FragmentIdentificationBinding
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.base.BaseFragment
import uz.digid.myverdi.ui.dialog.ConfirmDialog
import uz.digid.myverdi.ui.dialog.ConfirmDialogListener
import uz.digid.myverdi.validators.DateRule
import uz.digid.myverdi.validators.DocumentNumberRule


class IdentificationFragment : BaseFragment<FragmentIdentificationBinding, IdentificationViewModel>(),
    Validator.ValidationListener {
    private var mValidator: Validator? = null
    private var ignorEdittextChange = false
    private lateinit var person: PersonDetails
    override val layoutId: Int = R.layout.fragment_identification

    override fun createViewModel(): IdentificationViewModel {
        return IdentificationViewModel(Injection.providerRepository(), Injection.providerSchedulerProvider())
    }

    override fun initUI() {
        onBackPressed {
            requireActivity().finish()
        }
        binding.toolbar.inflateMenu(R.menu.exit_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.exit -> {
                    logout()
                    true
                }
                else -> false
            }
        }
        mValidator = Validator(this)
            .apply {
                setValidationListener(this@IdentificationFragment)
                put(binding.documentNumber, DocumentNumberRule())
            }

        val args: IdentificationFragmentArgs by navArgs()
        person = args.person
        binding.apply {

            scanBtn.setOnClickListener {
                val popupMenu = PopupMenu(requireContext(), it)
                popupMenu.inflate(R.menu.scan_menu)
                popupMenu.setOnMenuItemClickListener { item ->
                    return@setOnMenuItemClickListener when(item.itemId){
                        R.id.document -> {
                            person.docType = DocType.PASSPORT
                            val action =
                                IdentificationFragmentDirections.actionIdentificationFragmentToScannerMrzFragment(person)
                            findNavController().navigate(action)
                            hideKeyboard()
                            true
                        }
                        R.id.qr_code -> {
                            person.docType = DocType.ID_CARD
                            val action =
                                IdentificationFragmentDirections.actionIdentificationFragmentToScannerMrzFragment(person)
                            findNavController().navigate(action)
                            hideKeyboard()
                            true
                        }
                        else -> false
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popupMenu.setForceShowIcon(true)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                popupMenu.gravity = Gravity.CENTER
                }

                popupMenu.show()
            }

            nextBtn.setOnClickListener {
                validateFields()
            }
            dateOfBirth.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    validateFields()
                }
                false
            }
        }
        binding.documentNumber.addTextChangedListener(object : TextWatcher {
            private var characterAction = -1
            private var actionPosition = 0
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (count == 0 && after == 1) {
                    characterAction = 1
                } else if (count == 1 && after == 0) {
                    if (s[start] == ' ' && start > 0) {
                        characterAction = 3
                        actionPosition = start - 1
                    } else {
                        characterAction = 2
                    }
                } else {
                    characterAction = -1
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (ignorEdittextChange) {
                    return
                }
                var start: Int = binding.documentNumber.selectionStart
                val serialChars = "AEIOUBCDFGHJKLMNPQRSTVXZ"
                val numberChars = "0123456789"
                var str: String = binding.documentNumber.text.toString()
                if (characterAction == 3) {
                    str = str.substring(0, actionPosition) + str.substring(actionPosition + 1)
                    start--
                }
                val builder = StringBuilder(str.length)
                for (a in str.indices) {
                    val ch = str.substring(a, a + 1)
                    if (a > 1) {
                        if (numberChars.contains(ch)) {
                            builder.append(ch)
                        }
                    } else {
                        if (serialChars.contains(ch)) {
                            builder.append(ch)
                        }
                    }
                }
                ignorEdittextChange = true
                val format = "AB 123 45 67"
                var a = 0
                while (a < builder.length) {
                    if (a < format.length) {
                        if (format[a] == ' ') {
                            builder.insert(a, ' ')
                            a++
                            if (start == a && characterAction != 2 && characterAction != 3) {
                                start++
                            }
                        }
                    } else {
                        builder.insert(a, ' ')
                        if (start == a + 1 && characterAction != 2 && characterAction != 3) {
                            start++
                        }
                        break
                    }
                    a++
                }
                s.replace(0, s.length, builder)
                if (start >= 0) {
                    binding.documentNumber.setSelection(start.coerceAtMost(binding.documentNumber.length()))
                }
                ignorEdittextChange = false
            }
        })
        binding.dateOfBirth.addTextChangedListener(object : TextWatcher {
            private var characterAction = -1
            private var actionPosition = 0
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (count == 0 && after == 1) {
                    characterAction = 1
                } else if (count == 1 && after == 0) {
                    if (s[start] == ' ' && start > 0) {
                        characterAction = 3
                        actionPosition = start - 1
                    } else {
                        characterAction = 2
                    }
                } else {
                    characterAction = -1
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (ignorEdittextChange) {
                    return
                }
                var start: Int = binding.dateOfBirth.selectionStart
                val numberChars1 = "01"
                val numberChars2 = "0123"
                val numberChars = "0123456789"
                var str: String = binding.dateOfBirth.text.toString()
                if (characterAction == 3) {
                    str = str.substring(0, actionPosition) + str.substring(actionPosition + 1)
                    start--
                }
                val builder = StringBuilder(str.length)
                for (index in str.indices) {
                    val ch = str.substring(index, index + 1)

                    when (index) {
                        0 -> {
                            if (numberChars2.contains(ch)) {
                                builder.append(ch)
                            }
                        }
                        3 -> {
                            if (numberChars1.contains(ch)) {
                                builder.append(ch)
                            }
                        }
                        else -> {
                            if (numberChars.contains(ch)) {
                                builder.append(ch)
                            }
                        }
                    }

                }
                ignorEdittextChange = true
                val format = "29.07.1994"
                var a = 0
                while (a < builder.length) {
                    if (a < format.length) {
                        if (format[a] == '.') {
                            builder.insert(a, '.')
                            a++
                            if (start == a && characterAction != 2 && characterAction != 3) {
                                start++
                            }
                        }
                    } else {
                        builder.insert(a, '.')
                        if (start == a + 1 && characterAction != 2 && characterAction != 3) {
                            start++
                        }
                        break
                    }
                    a++
                }
                s.replace(0, s.length, builder)
                if (start >= 0) {
                    binding.dateOfBirth.setSelection(start.coerceAtMost(binding.dateOfBirth.length()))
                }
                ignorEdittextChange = false
            }
        })

        binding.dateOfExpired.addTextChangedListener(object : TextWatcher {
            private var characterAction = -1
            private var actionPosition = 0
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (count == 0 && after == 1) {
                    characterAction = 1
                } else if (count == 1 && after == 0) {
                    if (s[start] == ' ' && start > 0) {
                        characterAction = 3
                        actionPosition = start - 1
                    } else {
                        characterAction = 2
                    }
                } else {
                    characterAction = -1
                }
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (ignorEdittextChange) {
                    return
                }
                var start: Int = binding.dateOfExpired.selectionStart
                val numberChars1 = "01"
                val numberChars2 = "0123"
                val numberChars = "0123456789"
                var str: String = binding.dateOfExpired.text.toString()
                if (characterAction == 3) {
                    str = str.substring(0, actionPosition) + str.substring(actionPosition + 1)
                    start--
                }
                val builder = StringBuilder(str.length)
                for (index in str.indices) {
                    val ch = str.substring(index, index + 1)

                    when (index) {
                        0 -> {
                            if (numberChars2.contains(ch)) {
                                builder.append(ch)
                            }
                        }
                        3 -> {
                            if (numberChars1.contains(ch)) {
                                builder.append(ch)
                            }
                        }
                        else -> {
                            if (numberChars.contains(ch)) {
                                builder.append(ch)
                            }
                        }
                    }

                }
                ignorEdittextChange = true
                val format = "29.07.1994"
                var a = 0
                while (a < builder.length) {
                    if (a < format.length) {
                        if (format[a] == '.') {
                            builder.insert(a, '.')
                            a++
                            if (start == a && characterAction != 2 && characterAction != 3) {
                                start++
                            }
                        }
                    } else {
                        builder.insert(a, '.')
                        if (start == a + 1 && characterAction != 2 && characterAction != 3) {
                            start++
                        }
                        break
                    }
                    a++
                }
                s.replace(0, s.length, builder)
                if (start >= 0) {
                    binding.dateOfExpired.setSelection(start.coerceAtMost(binding.dateOfExpired.length()))
                }
                ignorEdittextChange = false
            }
        })

    }

    private fun logout() {
        ConfirmDialog(
            requireContext(),
            getString(R.string.log_out),
            R.string.no,
            R.string.yes,
            object : ConfirmDialogListener {
                override fun onClickPositiveButton() {
                    hideKeyboard()
                    mViewModel.deleteUser()
                    findNavController().popBackStack(R.id.splashFragment, false)
                }

                override fun onClickNegativeButton() {

                }
            }).show()
    }

    private fun validateFields() {
        try {
            mValidator?.apply {
                removeRules(binding.documentNumber)
                removeRules(binding.dateOfBirth)
                removeRules(binding.dateOfExpired)
                put(binding.documentNumber, DocumentNumberRule())
                put(binding.dateOfBirth, DateRule())
                put(binding.dateOfExpired, DateRule())
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        mValidator?.validate()
    }

    override fun onValidationSucceeded() {
        toSelfie()
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    fun toSelfie() {
        val perms =Manifest.permission.CAMERA
        if (EasyPermissions.hasPermissions(requireContext(), perms)) {
            val documentNumber = binding.documentNumber.text.toString()
            val dateOfBirth = binding.dateOfBirth.text.toString()
            val dateOfExpired = binding.dateOfExpired.text.toString()

            with(person) {
                serialNumber = documentNumber.replace(" ", "")
                birthDate = dateOfBirth
                expiryDate = dateOfExpired
               person.docType = DocType.PASSPORT
                personalNumber = null
            }
            val action = IdentificationFragmentDirections.actionIdentificationFragmentToNfcFragment(person)
            findNavController().navigate(action)
        } else {
            EasyPermissions.requestPermissions(this, "Camera permission denied", RC_CAMERA_PERM, perms)
        }
    }


    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        if (errors != null) {
            for (error in errors) {
                val view = error.view
                val message = error.getCollatedErrorMessage(context)

                if (view is EditText) {
                    view.error = message
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun subscribeToLiveData() {
       person.phoneNumber?.let { mViewModel.setPhoneNumber(it) }
       person.email?.let { mViewModel.setEmail(it) }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    companion object {
        val TAG = IdentificationFragment::class.java.canonicalName
        private const val RC_CAMERA_PERM = 123
    }

}*/
