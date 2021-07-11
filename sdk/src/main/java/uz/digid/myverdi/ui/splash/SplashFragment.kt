/*
package uz.digid.myverdi.ui.splash

import androidx.navigation.fragment.findNavController
import uz.digid.myverdi.R
import uz.digid.myverdi.data.model.PersonDetails
import uz.digid.myverdi.databinding.FragmentSplashBinding
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.MainActivity
import uz.digid.myverdi.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun createViewModel() = SplashViewModel(Injection.providerRepository())

    override val layoutId = R.layout.fragment_splash

    override fun initUI() {
        mViewModel.getUserData()
    }

    override fun subscribeToLiveData() {

        mViewModel.deviceSerialNumber.observe(viewLifecycleOwner) { serialNumber ->
            MainActivity.serialScannerNumber = serialNumber
            if (serialNumber.isNullOrEmpty()) {
                toIdentification()
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }

    private fun toIdentification() {
        mViewModel.phoneNumber.observe(viewLifecycleOwner) { phone ->
            if (phone.isNullOrEmpty()) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToEnterPhoneNumberFragment())
            } else {
                val person = PersonDetails().apply {
                    phoneNumber = phone
                }
                val action =
                    SplashFragmentDirections.actionSplashFragmentToIdentificationFragment(person)
                findNavController().navigate(action)
            }
        }
    }
}
*/
