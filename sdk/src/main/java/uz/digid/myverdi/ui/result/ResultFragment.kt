/*
package uz.digid.myverdi.ui.result

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import uz.digid.myverdi.R
import uz.digid.myverdi.common.IntentData
import uz.digid.myverdi.data.model.PersonDetails
import uz.digid.myverdi.data.model.info.ModelServiceInfo
import uz.digid.myverdi.data.model.info.ServiceInfo
import uz.digid.myverdi.data.model.request.*
import uz.digid.myverdi.databinding.FragmentResultBinding
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.MainActivity
import uz.digid.myverdi.ui.base.BaseFragment
import uz.digid.myverdi.ui.dialog.ConfirmDialog
import uz.digid.myverdi.ui.dialog.ConfirmDialogListener
import uz.digid.myverdi.ui.dialog.ProgressDialog
import uz.digid.myverdi.utils.extensions.toBase64
import uz.digid.myverdi.utils.util.AppUtil.md5
import java.util.*

class ResultFragment :
    BaseFragment<FragmentResultBinding, ResultViewModel>() {

    override fun createViewModel() = ResultViewModel(
        Injection.providerRepository(),
        Injection.providerSchedulerProvider()
    )

    val intent = Intent()
    private var publicKey: String = ""
    private var personDetails: PersonDetails? = null
    private var person: PersonDetails? = null
    private val args by navArgs<ResultFragmentArgs>()
    override val layoutId: Int = R.layout.fragment_result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personDetails = arguments?.getParcelable(IntentData.KEY_PERSON_DETAILS)
        if (personDetails == null) {
            person = args.person
        }
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), listOf(Manifest.permission.CAMERA).toTypedArray(), 100
            )
        }
    }

    override fun initUI() {

        if (personDetails != null) {
            person = personDetails
        }
        refreshData(person)

        binding.next.setOnClickListener {
            verify()
        }
        binding.cardViewFace.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun verify() {
        if (person != null) {
            val personDetails = person
            publicKey = UUID.randomUUID().toString()
            val guid = UUID.randomUUID().toString()
            val deviceID: String = Settings.Secure.getString(
                requireActivity().contentResolver,
                Settings.Secure.ANDROID_ID
            )
            val signString = md5(
                guid +
                        personDetails?.serialNumber + personDetails?.birthDate + personDetails?.expiryDate + publicKey
            )
            val passRequest = PassportRequest(
                personDetails?.serialNumber,
                personDetails?.personalNumber,
                personDetails?.docType,
                personDetails?.birthDate,
                personDetails?.expiryDate
            )
            val modelPersonRequest = ModelPersonRequest(passRequest)
            val answere = Answere(1, "OK")
            val base64Pass = personDetails?.imageFaceBase?.toBase64()
            val personPhoto = ModelPersonPhotoRequest(answere, person?.faceImageBase64, base64Pass)
            val model = Build.MODEL
            val modelMobileData =
                ModelMobileData(
                    model,
                    personDetails?.phoneNumber,
                    deviceID,
                    personDetails?.email
                )
            val serviceInfo = ServiceInfo()
            serviceInfo.scannerSerial = deviceID
            val modelServiceInfo = ModelServiceInfo()
            modelServiceInfo.serviceInfo = serviceInfo
            val passportRequest = PassportInfoRequest(
                guid,
                modelPersonRequest,
                personPhoto,
                modelServiceInfo,
                signString,
                publicKey,
                modelMobileData
            )

            val json = Gson().toJson(passportRequest)
            println(json)

            mViewModel.registration(passportRequest)

        }
    }

    override fun subscribeToLiveData() {
        var progressDialog: ProgressDialog? = null

        mViewModel.showMessage.observe(viewLifecycleOwner, {
           showMessage(it)
        })

        mViewModel.dataLoading.observe(viewLifecycleOwner, {
            if (it) {
                progressDialog = ProgressDialog(
                    requireContext(),
                    R.string.send_data,
                    R.raw.face_recognition
                )
                progressDialog?.show()
            } else {
                progressDialog?.dismiss()
            }
        })
        mViewModel.registration.observe(viewLifecycleOwner) { person ->
            if (person.clientData?.device?.serialNumber != null) {
                val serialNumber = person.clientData.device.serialNumber
                mViewModel.setPublicKey(publicKey)
                mViewModel.setDeviceSerialNumber(serialNumber)
                MainActivity.serialScannerNumber = serialNumber
            } else {
                Toast.makeText(requireContext(),"serialNumber: null",Toast.LENGTH_LONG).show()
            }
            val action =
                ResultFragmentDirections.actionResultDataFragmentToBasePassportTabFragment(person)
            findNavController().navigate(action)
        }
    }

    private fun refreshData(person: PersonDetails?) {
        if (person == null) {
            return
        }
        binding.apply {
            valuePassportNumber.text = person.serialNumber
            if (person.faceImage != null) {
                binding.imagePassport.setImageBitmap(person.faceImage)
                binding.lastName.text = person.name
                binding.firstName.text = person.surname
            } else {
                binding.personContent.visibility = View.GONE
            }
            if (person.expiryDate.isNullOrEmpty()) {
                binding.labelExpirationDate.visibility = View.GONE
                binding.valueExpirationDate.visibility = View.GONE
            } else {
                valueExpirationDate.text = person.expiryDate
            }
            if (person.birthDate.isNullOrEmpty()) {
                binding.valueDateOfBirth.visibility = View.GONE
                binding.labelIssuingState.visibility = View.GONE
            } else {
                valueDateOfBirth.text = person.birthDate
            }
            if (person.personalNumber.isNullOrEmpty()) {
                binding.valuePersonalNumber.visibility = View.GONE
                binding.rowPersonalNumber.visibility = View.GONE
            } else {
                valuePersonalNumber.text = person.personalNumber
            }
            if (person.imageFaceBase != null) {
                imageFace.setImageBitmap(person.imageFaceBase)
                binding.next.visibility = View.VISIBLE
            }
        }
    }
    private fun showMessage(message: String){
        ConfirmDialog(
            requireContext(),
            message,
            R.string.ok,
            R.string.retry,
            object :
                ConfirmDialogListener {
                override fun onClickPositiveButton() {
                    verify()
                }

                override fun onClickNegativeButton() {
                    findNavController().popBackStack()
                }
            }
        ).show()
    }
}
*/
