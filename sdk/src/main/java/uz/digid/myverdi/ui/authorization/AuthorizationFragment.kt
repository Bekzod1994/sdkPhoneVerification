/*
package uz.digid.myverdi.ui.authorization

import android.annotation.SuppressLint
import android.os.Build
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import uz.digid.myverdi.R
import uz.digid.myverdi.data.model.PersonDetails
import uz.digid.myverdi.data.model.info.ModelServiceInfo
import uz.digid.myverdi.data.model.info .ServiceInfo
import uz.digid.myverdi.data.model.request.ModelMobileData
import uz.digid.myverdi.data.model.request.ModelPersonPhotoRequest
import uz.digid.myverdi.data.model.request.PassportInfoRequest
import uz.digid.myverdi.databinding.FragmentAuthorizationBinding
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.MainActivity
import uz.digid.myverdi.ui.base.BaseFragment
import uz.digid.myverdi.ui.dialog.ProgressDialog
import uz.digid.myverdi.utils.extensions.toBase64
import uz.digid.myverdi.utils.util.AppUtil.md5
import java.util.*

class classAuthorizationFragment : BaseFragment<FragmentAuthorizationBinding, AuthorizationViewModel>() {
    override val layoutId = R.layout.fragment_authorization
    private lateinit var person: PersonDetails
    private val args by navArgs<AuthorizationFragmentArgs>()

    override fun createViewModel(): AuthorizationViewModel {
        return AuthorizationViewModel(
            Injection.providerRepository(),
            Injection.providerSchedulerProvider()
        )
    }

    override fun initUI() {
        person = args.person
        binding.faceImage.apply {
            visibility = View.VISIBLE
            setImageBitmap(person.imageFaceBase)
        }
    }

    @SuppressLint("SetTextI18n", "HardwareIds")
    override fun subscribeToLiveData() {

        binding.sendDataButton.setOnClickListener {
            verification()
        }

        var progressDialog: ProgressDialog? = null

        mViewModel.dataLoading.observe(viewLifecycleOwner, {
            if (it) {
                progressDialog =
                    ProgressDialog(requireContext(), R.string.send_data, R.raw.face_recognition)
                progressDialog?.show()
            } else {
                progressDialog?.dismiss()
            }
        })
        mViewModel.showMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        mViewModel.authorization.observe(viewLifecycleOwner) {
           val action = AuthorizationFragmentDirections.actionAuthorizationFragmentToBasePassportTabFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun verification() {
        val deviceSerialNumber: String? = MainActivity.serialScannerNumber
        val guid = UUID.randomUUID().toString()
        val deviceID: String = Settings.Secure.getString(
            requireActivity().contentResolver,
            Settings.Secure.ANDROID_ID
        )
        val signString = md5(buildString {
            guid + deviceSerialNumber + deviceID + MainActivity.publicKey
        })

        val personPhoto = ModelPersonPhotoRequest(null, null, person.imageFaceBase?.toBase64())
        val model = Build.MODEL
        val modelMobileData =
            ModelMobileData(model, null, deviceID, null)
        val serviceInfo = ServiceInfo()
        serviceInfo.scannerSerial = deviceSerialNumber
        val modelServiceInfo = ModelServiceInfo()
        modelServiceInfo.serviceInfo = serviceInfo
        val passportRequest = PassportInfoRequest(
            guid,
            null,
            personPhoto,
            modelServiceInfo,
            signString,
            guid,
            modelMobileData
        )

        val json = Gson().toJson(passportRequest)
        println(json)

        mViewModel.verification(passportRequest)
    }


}*/
