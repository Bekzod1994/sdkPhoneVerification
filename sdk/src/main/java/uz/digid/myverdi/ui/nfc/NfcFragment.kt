/*
package uz.digid.myverdi.ui.nfc

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.reactivex.BackpressureStrategy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import net.sf.scuba.smartcards.CardService
import org.jmrtd.BACKey
import org.jmrtd.BACKeySpec
import org.jmrtd.PassportService
import org.jmrtd.lds.icao.DG1File
import org.jmrtd.lds.icao.DG2File
import org.jmrtd.lds.iso19794.FaceImageInfo
import uz.digid.myverdi.R
import uz.digid.myverdi.data.model.DocType
import uz.digid.myverdi.data.model.PersonDetails
import uz.digid.myverdi.databinding.FragmentNfcBinding
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.MainActivity
import uz.digid.myverdi.ui.base.BaseFragment
import uz.digid.myverdi.ui.dialog.ErrorNfcDialog
import uz.digid.myverdi.utils.util.DateUtil
import uz.digid.myverdi.utils.util.Image
import uz.digid.myverdi.utils.util.ImageUtil
import java.util.*

@Suppress("UNREACHABLE_CODE")
class NfcFragment : BaseFragment<FragmentNfcBinding, NfcViewModel>() {
    var adapter: NfcAdapter? = null
    var passportNumber: String? = null
    private var expirationDate: String? = null
    private var birthDate: String? = null
    lateinit var person: PersonDetails
    var isCheckedDoc = false

    private var mHandler = Handler(Looper.getMainLooper())
    private var stepNfc: MutableLiveData<Int>? = null

    override val layoutId: Int = R.layout.fragment_nfc
    override fun createViewModel() = NfcViewModel(
        Injection.providerRepository(),
        Injection.providerSchedulerProvider()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val filter = IntentFilter(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)
        requireActivity().registerReceiver(mReceiver, filter)
        val args by navArgs<NfcFragmentArgs>()
        person = args.person
    }

    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action
            if (action == NfcAdapter.ACTION_ADAPTER_STATE_CHANGED) {
                val state = intent.getIntExtra(
                    NfcAdapter.EXTRA_ADAPTER_STATE,
                    NfcAdapter.STATE_OFF
                )
                when (state) {
                    NfcAdapter.STATE_OFF -> {
                        checkNfcState()
                    }
                    NfcAdapter.STATE_TURNING_OFF -> {
                        Log.d("NFC", "STATE_TURNING_OFF")
                    }
                    NfcAdapter.STATE_ON -> {
                        checkNfcState()
                    }
                    NfcAdapter.STATE_TURNING_ON -> {
                        Log.d("NFC", "STATE_TURNING_ON")
                    }
                }
            }
        }
    }


    override fun initUI() {
        isCheckedDoc = false
        passportNumber = person.serialNumber
        expirationDate = DateUtil.changeFormatYYDDMM(person.expiryDate!!)
        birthDate = DateUtil.changeFormatYYDDMM(person.birthDate!!)
        binding.apply {
            valuePassportNumber.text = person.serialNumber
            valueDateOfBirth.text = person.birthDate
            valueExpirationDate.text = person.expiryDate
            if(person.personalNumber.isNullOrEmpty()) {
                binding.rowPersonalNumber.visibility = View.GONE
            } else {
                valuePersonalNumber.text = person.personalNumber
            }
            adapter = NfcAdapter.getDefaultAdapter(requireContext())
            buttonNext.setOnClickListener {
                isCheckedDoc = true
                showAnimation()
                buttonNext.visibility = View.GONE
            }
            skipButton.setOnClickListener {
                val action =
                    NfcFragmentDirections.actionNfcFragmentToSelfieFragment(person, false)
                findNavController().navigate(action)
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun doSomeWork() {
        val observable = PublishSubject.create<Int>()
        observable
            .toFlowable(BackpressureStrategy.DROP)
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    println("The Number Is: $it")
                },
                { t ->
                    print(t.message)
                }
            )
    }


    override fun subscribeToLiveData() {
        stepNfc = MutableLiveData()
        stepNfc?.observe(viewLifecycleOwner, { step ->
            when (step) {
                1 -> binding.animationView.apply {
                    setAnimation(R.raw.step_progress)
                    speed = 3f
                    setMaxProgress(0.33f)
                    playAnimation()
                    Log.d("Step", "1")

                }
                2 -> binding.animationView.apply {
                    setMaxProgress(0.63f)
                    resumeAnimation()
                    Log.d("Step", "2")
                }
                3 -> binding.animationView.apply {
                    setMaxProgress(1f)
                    resumeAnimation()
                    Log.d("Step", "3")

                }
            }

        })
    }

    private fun showAnimation() {
        if (isCheckedDoc && adapter != null) {
            if (adapter!!.isEnabled) {
                binding.animationView.apply {
                    visibility = View.VISIBLE
                    playAnimation()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        doSomeWork()
        checkNfcState()

    }

    private fun checkNfcState() {
        if (adapter != null) {
            if (adapter!!.isEnabled) {
                showAnimation()
                binding.textViewNfcTitle.visibility = View.INVISIBLE
                val intent = Intent(requireContext(), requireActivity().javaClass)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                val pendingIntent =
                    PendingIntent.getActivity(
                        requireContext(),
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                val filter = arrayOf(arrayOf("android.nfc.tech.IsoDep"))
                if (activity != null)
                    adapter!!.enableForegroundDispatch(
                        requireActivity(),
                        pendingIntent,
                        null,
                        filter
                    )
            } else {
                binding.textViewNfcTitle.apply {
                    setText(R.string.nfc_is_not_enabled)
                    visibility = View.VISIBLE
                }
            }
        } else {
            binding.textViewNfcTitle.apply {
                setText(R.string.nfc_is_not_available)
                visibility = View.VISIBLE
            }
            binding.buttonNext.setOnClickListener {
                val action =
                    NfcFragmentDirections.actionNfcFragmentToSelfieFragment(person,false)
                findNavController().navigate(action)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        onDisableNfc()
    }

    fun onDisableNfc() {
        val nfcAdapter = NfcAdapter.getDefaultAdapter(requireContext())
        nfcAdapter?.disableForegroundDispatch(requireActivity())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.onIntentListener = object : IntentListener {
            override fun onNewIntent(intent: Intent?) {
                if (!isCheckedDoc) {

                    Toast.makeText(
                        requireContext(),
                        "проверить точность данных",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                if (intent != null) {
                    stepNfc?.postValue(1)
                    onNFCSReadStart()
                    if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action) {
                        val tag = intent.extras?.getParcelable<Tag>(NfcAdapter.EXTRA_TAG)

                        if (listOf(*tag!!.techList).contains("android.nfc.tech.IsoDep")) {
                            if (passportNumber != null && passportNumber!!.isNotEmpty()
                                && expirationDate != null && expirationDate!!.isNotEmpty()
                                && birthDate != null && birthDate!!.isNotEmpty()

                            ) {
                                val bacKey: BACKeySpec =
                                    BACKey(passportNumber, birthDate, expirationDate)
                                ReadTask(IsoDep.get(tag), bacKey)
                                    .execute()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unregisterReceiver(mReceiver)
    }


    @SuppressLint("StaticFieldLeak")
    inner class ReadTask constructor(
        private val isoDep: IsoDep,
        private val bacKey: BACKeySpec
    ) : AsyncTask<Void?, Void?, Exception?>() {
        var docType = DocType.ID_CARD

        override fun doInBackground(vararg params: Void?): Exception? {
            try {
                val cardService = CardService.getInstance(isoDep)
                cardService.open()
                val service = PassportService(
                    cardService,
                    PassportService.NORMAL_MAX_TRANCEIVE_LENGTH,
                    PassportService.DEFAULT_MAX_BLOCKSIZE,
                    false,
                    true
                )
                service.open()
                val paceSucceeded = false
                service.sendSelectApplet(paceSucceeded)
                if (!paceSucceeded) {
                    try {
                        service.getInputStream(PassportService.EF_COM).read()
                    } catch (e: Exception) {
                        Log.w("DDD", e)
                        service.doBAC(bacKey)
                    }
                }

                // -- Personal Details -- //
                Log.d("Step", "Step1")
                val dg1In = service.getInputStream(PassportService.EF_DG1)
                Log.d("Step", "Step2")
                val dg1File = DG1File(dg1In)
                Log.d("Step", "Step3")
                val mrzInfo = dg1File.mrzInfo
                person.name = (
                        mrzInfo.secondaryIdentifier.replace("<", " ").trim { it <= ' ' })
                person.surname = (
                        mrzInfo.primaryIdentifier.replace("<", " ").trim { it <= ' ' })
                person.personalNumber = mrzInfo.personalNumber
                person.gender = mrzInfo.gender.toString()
                person.serialNumber = mrzInfo.documentNumber
                person.nationality = mrzInfo.nationality
                if ("I" == mrzInfo.documentCode) {
                    docType = DocType.ID_CARD
                } else if ("P" == mrzInfo.documentCode) {
                    docType = DocType.PASSPORT
                }
                stepNfc?.postValue(2)
                // -- Face Image -- //
                val dg2In = service.getInputStream(PassportService.EF_DG2)
                val dg2File = DG2File(dg2In)
                stepNfc?.postValue(3)
                val faceInfos = dg2File.faceInfos
                val allFaceImageInfos: MutableList<FaceImageInfo> = ArrayList()
                for (faceInfo in faceInfos) {
                    allFaceImageInfos.addAll(faceInfo.faceImageInfos)
                }
                if (allFaceImageInfos.isNotEmpty()) {
                    val faceImageInfo = allFaceImageInfos.iterator().next()
                    val image: Image = ImageUtil.getImage(requireContext(), faceImageInfo)
                    person.faceImage = image.bitmapImage
                    person.faceImageBase64 = image.base64Image
                }
                Log.d("DDD", "Face Image")

                person.docType = docType
            } catch (e: Exception) {
                Log.w("TTT", e.toString() + e.message)
                onNFCReadFinish()

                return e
            }
            return null
        }

        override fun onPostExecute(exception: Exception?) {
            when (exception) {
                is AccessDeniedException -> {
                    Log.d("EEE", "AccessDeniedException")
                }
            }
            adapter = null
            if (exception == null) {
                onDisableNfc()
                val action =
                    NfcFragmentDirections.actionNfcFragmentToSelfieFragment(person,false )
                mHandler.postDelayed({
                    findNavController().navigate(action)
                    stepNfc = null
                    binding.animationView.apply {
                        visibility = View.INVISIBLE
                        setAnimation(R.raw.passport_nfc)
                    }
                }, 600)

            } else {
                mHandler.post {
                    val dialog = ErrorNfcDialog(requireContext(), R.string.nfc, R.string.error_nfc)
                    dialog.nextListener {
                        if (it) {
                            val action =
                                NfcFragmentDirections.actionNfcFragmentToSelfieFragment(
                                    person
                                )
                            mHandler.postDelayed({
                                findNavController().navigate(action)
                            }, 600)
                        } else {
                            findNavController().popBackStack()
                        }
                    }
                    dialog.show()

                }
            }
        }
    }

    private fun onNFCSReadStart() {
        Log.d("TTT", "onNFCSReadStart")
        mHandler.post { binding.progressBar.visibility = View.VISIBLE }

    }

    private fun onNFCReadFinish() {
        Log.d("TTT", "onNFCReadFinish")
        mHandler.post { binding.progressBar.visibility = View.GONE }
    }


}



*/
