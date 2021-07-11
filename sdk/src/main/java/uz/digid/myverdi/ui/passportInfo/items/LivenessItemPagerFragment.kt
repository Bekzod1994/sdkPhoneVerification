/*
package uz.digid.myverdi.ui.passportInfo.items

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import uz.digid.myverdi.R
import uz.digid.myverdi.data.model.info.ModelPersonAnswere
import uz.digid.myverdi.databinding.LivenessFragmentBinding
import uz.digid.myverdi.utils.util.ImageUtil
import java.math.RoundingMode

class LivenessItemPagerFragment(private val passport: ModelPersonAnswere) : Fragment() {

    private lateinit var binding: LivenessFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.liveness_fragment, container, false)
        setData(passport)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    private fun setData(passport: ModelPersonAnswere) {
        val photoData = passport.modelPersonPhoto
        val livenessScore = passport.livenessAnswere?.validateResponse?.livenessScore?.liveness ?: -1.0
        val similarityScore = passport.livenessAnswere?.validateResponse?.similarityScore?.similarity ?: -1.0
        val base64 = photoData?.personPhoto
        val bitmapPassport = ImageUtil.convert(base64)
        if (bitmapPassport != null) {
            binding.imagePassport.setImageBitmap(bitmapPassport)
        }
        val bitmapSelfie = ImageUtil.convert(photoData?.additional)
        if (bitmapSelfie != null) {
            binding.imageSelfie.setImageBitmap(bitmapSelfie)
        }

        binding.nameLiveness.setText(R.string.liveness)
        binding.nameSimilarity.setText(R.string.similarity)
        binding.valueLiveness.text =
            (if (livenessScore != -1.0) {
                livenessScore.toBigDecimal().setScale(2, RoundingMode.UP)
            } else {
                -1
            }).toString()
        binding.valueSimilarity.text =
            (if (livenessScore != -1.0) {
                similarityScore.toBigDecimal().setScale(2, RoundingMode.UP)
            } else {
                -1
            }).toString()

        if (livenessScore > 0.79) {
            binding.imageLiveness
                .setImageResource(R.drawable.ic_check)
        }
        if (similarityScore > 0.46) {
            binding.imageSimilarity
                .setImageResource(R.drawable.ic_check)
        }
        if ((livenessScore > 0.79 && similarityScore > 0.46)) {
            binding.apply {
                info.apply {
                    setText(R.string.register_success)
                    setTextColor(R.color.item_color)
                }
            }
        } else {
            binding.apply {
                info.apply {
                    setText(R.string.register_error)
                    setTextColor(R.color.design_default_color_error)
                }
            }
        }
    }
}*/
