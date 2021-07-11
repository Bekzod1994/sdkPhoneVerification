/*
package uz.digid.myverdi.ui.login

import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import uz.digid.myverdi.R
import uz.digid.myverdi.data.model.PersonDetails
import uz.digid.myverdi.databinding.FragmentVerifySmsCodeBinding
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.base.BaseFragment
import java.util.*

class VerifySmsCodeFragment : BaseFragment<FragmentVerifySmsCodeBinding, VerifySmsCodeViewModel>() {

    private lateinit var person: PersonDetails
    override val layoutId: Int = R.layout.fragment_verify_sms_code

    override fun createViewModel() = VerifySmsCodeViewModel(
        Injection.providerRepository(),
        Injection.providerSchedulerProvider()
    )

    override fun initUI() {
        val args: VerifySmsCodeFragmentArgs by navArgs()

        binding.codeInput.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    verifyCode()
                    return@setOnEditorActionListener true
                }
                false
            }
            requestFocus()
        }

        person = args.person
        val phoneNumber = person.phoneNumber
        val phoneNumberDisplay =
            PhoneNumberUtils.formatNumber("+$phoneNumber", Locale.getDefault().country)
        binding.materialToolbar.title = phoneNumberDisplay
        binding.nextBtn.setOnClickListener {

           verifyCode()
        }
        binding.sentCodeBtn.setOnClickListener {
            phoneNumber?.let { phoneNumber ->
                mViewModel.verifyPhoneNumber(phoneNumber)
            }
        }
        binding.viewModel = mViewModel

    }

    override fun subscribeToLiveData() {
        mViewModel.verifyPhoneNumberWithCode.observe(viewLifecycleOwner) {
            val action =
                VerifySmsCodeFragmentDirections.actionEnterSmsCodeFragmentToIdentificationFragment(person)
            findNavController().navigate(action)
        }
        mViewModel.showMessage.observe(viewLifecycleOwner) {
            Snackbar.make(binding.codeInput, it, Snackbar.LENGTH_LONG).show()
        }
        mViewModel.verifyPhoneNumber.observe(viewLifecycleOwner) {
            Snackbar.make(binding.codeInput, "отправлен", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun verifyCode() {
        val code = binding.codeInput.rawText
        if (TextUtils.isEmpty(code)) {
            binding.codeInput.error = getString(R.string.cannot_empty)
            return
        }
        person.phoneNumber?.let { phoneNumber ->
            mViewModel.verifyPhoneNumberWithCode(
                phoneNumber, code
            )
        }
    }
}*/
