/*
package uz.digid.myverdi.ui.camera.scanner

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableBoolean
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.mlkit.common.MlKitException
import org.jmrtd.lds.icao.MRZInfo
import uz.digid.myverdi.GraphicOverlay
import uz.digid.myverdi.R
import uz.digid.myverdi.data.model.DocType
import uz.digid.myverdi.data.model.PersonDetails
import uz.digid.myverdi.databinding.FragmentMrzScannerBinding
import uz.digid.myverdi.mlkit.VisionImageProcessor
import uz.digid.myverdi.mlkit.barcodescanner.BarcodeScannerProcessor
import uz.digid.myverdi.mlkit.text.TextRecognitionProcessor
import uz.digid.myverdi.ui.base.BaseFragment
import uz.digid.myverdi.utils.PreferenceUtils
import uz.digid.myverdi.utils.PublicMethods
import uz.digid.myverdi.utils.util.DateUtil

class ScannerMrzFragment :
    BaseFragment<FragmentMrzScannerBinding, ScannerViewModel>(),
    ActivityCompat.OnRequestPermissionsResultCallback,
    TextRecognitionProcessor.ResultListener,
    CompoundButton.OnCheckedChangeListener {
    private val args by navArgs<ScannerMrzFragmentArgs>()
    private var previewView: PreviewView? = null
    private var graphicOverlay: GraphicOverlay? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private var preview: Preview? = null
    private var imageAnalysis: ImageAnalysis? = null
    private var person = PersonDetails()
    private var imageProcessor: VisionImageProcessor? = null
    private var needUpdateGraphicOverlayImageSourceInfo = false
    private var selectedModel = DocType.PASSPORT
    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private var cameraSelector: CameraSelector? = null
    override val layoutId: Int = R.layout.fragment_mrz_scanner
    val isQrCode = ObservableBoolean(false)
    override fun createViewModel() = ScannerViewModel(requireContext())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        person = args.person
        selectedModel = person.docType!!
        if (selectedModel == DocType.ID_CARD) {
            isQrCode.set(true)
        }
        cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
        if (!PublicMethods.allPermissionsGranted(requireActivity())) {
            PublicMethods.runtimePermissions(requireActivity())
        }

    }

    override fun initUI() {
        previewView = binding.previewView
        binding.fragment = this
        graphicOverlay = binding.graphicOverlay
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun subscribeToLiveData() {
        mViewModel
            .getProcessCameraProvider()
            ?.observe(
                this,
                { provider: ProcessCameraProvider? ->
                    cameraProvider = provider
                    if (PublicMethods.allPermissionsGranted(requireActivity())) {
                        bindAllCameraUseCases()
                    }
                }
            )
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        Log.d(TAG, "Set facing")
        if (cameraProvider == null) {
            return
        }
        val newLensFacing = if (lensFacing == CameraSelector.LENS_FACING_FRONT) {
            CameraSelector.LENS_FACING_BACK
        } else {
            CameraSelector.LENS_FACING_FRONT
        }
        val newCameraSelector =
            CameraSelector.Builder().requireLensFacing(newLensFacing).build()
        try {
            if (cameraProvider!!.hasCamera(newCameraSelector)) {
                lensFacing = newLensFacing
                cameraSelector = newCameraSelector
                bindAllCameraUseCases()
                return
            }
        } catch (e: CameraInfoUnavailableException) {
            // Falls through
        }
    }

    override fun onResume() {
        super.onResume()
        bindAllCameraUseCases()
    }

    override fun onPause() {
        super.onPause()

        imageProcessor?.run {
            this.stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        imageProcessor?.run {
            this.stop()
        }
    }

    private fun bindAllCameraUseCases() {
        if (cameraProvider != null) {
            // As required by CameraX API, unbinds all use cases before trying to re-bind any of them.
            cameraProvider!!.unbindAll()
            bindPreviewUseCase()
            bindAnalysisUseCase()
        }
    }

    private fun bindPreviewUseCase() {
        if (PreferenceUtils.isCameraLiveViewportEnabled(requireContext())) {
            return
        }
        if (cameraProvider == null) {
            return
        }
        if (preview != null) {
            cameraProvider!!.unbind(preview)
        }

        val builder = Preview.Builder()
        val targetResolution = PreferenceUtils.getCameraXTargetResolution(requireContext())
        if (targetResolution != null) {
            builder.setTargetResolution(targetResolution)
        }
        preview = builder.build()
        preview!!.setSurfaceProvider(previewView!!.surfaceProvider)
        cameraProvider!!.bindToLifecycle(*/
/* lifecycleOwner= *//*
this, cameraSelector!!, preview)
    }

    private fun bindAnalysisUseCase() {
        if (cameraProvider == null) {
            return
        }
        if (imageAnalysis != null) {
            cameraProvider!!.unbind(imageAnalysis)
        }
        if (imageProcessor != null) {
            imageProcessor!!.stop()
        }
        imageProcessor = try {
            when (selectedModel) {
                DocType.PASSPORT -> {
                    TextRecognitionProcessor(requireContext(), this)
                }
                DocType.ID_CARD -> {
                    Log.i(
                        TAG,
                        "Using Barcode Detector Processor"
                    )
                    BarcodeScannerProcessor(requireContext(), this)
                }
                else -> throw IllegalStateException("Invalid model name")
            }
        } catch (e: Exception) {
            Log.e(
                TAG,
                "Can not create image processor: $selectedModel",
                e
            )
            Toast.makeText(
                requireContext(),
                "Can not create image processor: " + e.localizedMessage,
                Toast.LENGTH_LONG
            )
                .show()
            return
        }

        val builder = ImageAnalysis.Builder()
        val targetResolution = PreferenceUtils.getCameraXTargetResolution(requireContext())
        if (targetResolution != null) {
            builder.setTargetResolution(targetResolution)
        }
        imageAnalysis = builder.build()

        needUpdateGraphicOverlayImageSourceInfo = true

        imageAnalysis?.setAnalyzer(
            ContextCompat.getMainExecutor(requireContext()),
            { imageProxy: ImageProxy ->
                if (needUpdateGraphicOverlayImageSourceInfo) {
                    val isImageFlipped =
                        lensFacing == CameraSelector.LENS_FACING_FRONT
                    val rotationDegrees =
                        imageProxy.imageInfo.rotationDegrees
                    if (rotationDegrees == 0 || rotationDegrees == 180) {
                        graphicOverlay!!.setImageSourceInfo(
                            imageProxy.width, imageProxy.height, isImageFlipped
                        )
                    } else {
                        graphicOverlay!!.setImageSourceInfo(
                            imageProxy.height, imageProxy.width, isImageFlipped
                        )
                    }
                    needUpdateGraphicOverlayImageSourceInfo = false
                }
                try {
                    imageProcessor!!.processImageProxy(imageProxy, graphicOverlay)
                } catch (e: MlKitException) {
                    Log.e(
                        TAG,
                        "Failed to process image. Error: " + e.localizedMessage
                    )

                }
            }
        )
        cameraProvider!!.bindToLifecycle( */
/* lifecycleOwner= *//*
this, cameraSelector!!, imageAnalysis)
        try {
            // A variable number of use-cases can be passed here -
            // camera provides access to CameraControl & CameraInfo
            val camera = cameraProvider!!.bindToLifecycle(
                this, cameraSelector!!, preview
            )
            binding.flashButton.setOnClickListener {
                if (it.tag.equals("of")) {
                    camera.cameraControl.enableTorch(true)
                    it.tag = "on"
                } else {
                    camera.cameraControl.enableTorch(false)
                    it.tag = "of"
                }

            }
            // Attach the viewfinder's surface provider to preview use case
            //  preview?.setSurfaceProvider(viewFinder.surfaceProvider)
        } catch (exc: Exception) {
            Log.e(TAG, "Use case binding failed", exc)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.i(TAG, "Permission granted!")
        if (PublicMethods.allPermissionsGranted(requireActivity())) {
            bindAllCameraUseCases()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val TAG = "CameraXLivePreview"
    }

    override fun onSuccess(mrzInfo: MRZInfo?) {
        Log.i("TTT", "onSuccess")
        val personDetails = person ?: PersonDetails()
        with(personDetails) {
            serialNumber = mrzInfo?.documentNumber
            expiryDate = DateUtil.convertFromMrzDate(mrzInfo?.dateOfExpiry)
            birthDate = DateUtil.convertFromMrzDate(mrzInfo?.dateOfBirth)
            personalNumber = mrzInfo?.personalNumber
        }
        person.docType = if (isQrCode.get()) {
            DocType.ID_CARD
        } else {
            DocType.PASSPORT
        }
        person = personDetails
    }

    override fun onError(exp: Exception?) {
        Log.d("FFF", exp.toString())
    }

    override fun onDetect(results: Boolean) {
        val visibility: Int = if (results) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
        binding.verticalTextViewMrz.visibility = visibility
        binding.verticalTextViewMrz2.visibility = visibility
    }


}
*/
