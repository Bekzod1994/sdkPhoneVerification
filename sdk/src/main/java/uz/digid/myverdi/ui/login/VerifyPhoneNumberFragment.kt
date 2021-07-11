/*
package uz.digid.myverdi.ui.login

import android.os.Bundle
import android.util.Log.d
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.digid.myverdi.R
import uz.digid.myverdi.data.model.PersonDetails
import uz.digid.myverdi.databinding.FragmentVerifyPhoneNumberBinding
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.base.BaseFragment
import uz.digid.myverdi.utils.util.AppUtil


class VerifyPhoneNumberFragment :
    BaseFragment<FragmentVerifyPhoneNumberBinding, VerifyPhoneNumberViewModel>() {
    private lateinit var person: PersonDetails

    override val layoutId: Int = R.layout.fragment_verify_phone_number
    override fun createViewModel() = VerifyPhoneNumberViewModel(
        Injection.providerRepository(),
        Injection.providerSchedulerProvider()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressed {
            requireActivity().finish()
        }
    }

    override fun initUI() {

        binding.phoneInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.email.requestFocus()
                return@setOnEditorActionListener true
            }
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sendSmsCode()
            }
            false
        }

        binding.email.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                sendSmsCode()
                return@setOnEditorActionListener true
            }
            false
        }

        binding.checkbox.setOnClickListener {
            setSendButtonStatus()
        }

        binding.nextBtn.setOnClickListener {
            if (binding.checkbox.isChecked){
                sendSmsCode()
            }
        }

        binding.publicOfferButton.setOnClickListener{
           val action = VerifyPhoneNumberFragmentDirections.actionEnterPhoneNumberFragmentToPublicOfferFragment()
            findNavController().navigate(action)
            hideKeyboard()
        }

        binding.viewModel = mViewModel
    }
    private fun setSendButtonStatus() {
        if(binding.checkbox.isChecked){
            binding.nextBtn.apply {
                setBackgroundResource(R.drawable.bg_button_access)
                isClickable = true
                isFocusable = true
            }
        } else {
            binding.nextBtn.apply {
                setBackgroundResource(R.drawable.bg_button_not_access)
                isClickable = false
                isFocusable = false
            }
        }
    }

    private fun sendSmsCode() {
        val phoneNumberText = binding.phoneInput.rawText
        when {
            phoneNumberText.isNullOrEmpty() -> {
                Toast.makeText(requireContext(), "Enter phone number", Toast.LENGTH_SHORT)
                    .show()
            }
            phoneNumberText.length != 9 -> {
                Toast.makeText(requireContext(), "Short phone number ", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                val phoneNumber = "998$phoneNumberText"
                person = PersonDetails().apply {
                    this.email = binding.email.text.toString()
                    this.phoneNumber = phoneNumber
                }
                mViewModel.verifyPhoneNumber(phoneNumber)

            }
        }
        d("TTT", phoneNumberText)
    }

    override fun onResume() {
        super.onResume()
        if (binding.phoneInput.rawText.isEmpty()) {
            binding.phoneInput.requestFocus()
            AppUtil.showKeyboard(binding.phoneInput)
        }
        setSendButtonStatus()
    }

    override fun subscribeToLiveData() {
        mViewModel.verifyPhoneNumber.observe(viewLifecycleOwner) {
            val action =
                VerifyPhoneNumberFragmentDirections.actionEnterPhoneNumberFragmentToEnterSmsCodFragment(person)
            findNavController().navigate(action)
        }
        mViewModel.showMessage.observe(viewLifecycleOwner) {
            Snackbar.make(binding.phoneInput, it, Snackbar.LENGTH_LONG).show()
        }
    }
}*/
