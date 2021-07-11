/*
package uz.digid.myverdi.ui.passportInfo

import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import uz.digid.myverdi.R
import uz.digid.myverdi.databinding.FragmentInfoPassportBinding
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.adapters.PassportDataPagerAdapter
import uz.digid.myverdi.ui.base.BaseFragment
import uz.digid.myverdi.utils.util.ImageUtil

class BasePassportTabFragment : BaseFragment<FragmentInfoPassportBinding, PassportInfoViewModel>() {

    private val args: BasePassportTabFragmentArgs by navArgs()
    override fun createViewModel() =
        PassportInfoViewModel(Injection.providerRepository(), Injection.providerSchedulerProvider())

    override val layoutId = R.layout.fragment_info_passport

    override fun initUI() {
        val passData = args.person
        val livenessScore =
            passData.livenessAnswere?.validateResponse?.livenessScore?.liveness ?: -1.0
        val similarityScore =
            passData.livenessAnswere?.validateResponse?.similarityScore?.similarity ?: -1.0
        if ((livenessScore > 0.79 && similarityScore > 0.46)) {
            binding.goMainBtn.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    val action =
                        BasePassportTabFragmentDirections.actionBasePassportTabFragmentToMainFragment()
                    findNavController().navigate(action)
                }
            }
        } else {
            binding.apply {
                binding.goMainBtn.visibility = View.GONE
            }
        }
        binding.firstName.text = passData.person?.nameL
        binding.lastName.text = passData.person?.surnameL
        binding.textView2.text = passData.person?.sexName
        val base64 = passData.modelPersonPhoto?.personPhoto
        if (base64 != null) {
            if (base64.isNotEmpty()) {
                val bitmap = ImageUtil.convert(base64)
                binding.image.setImageBitmap(bitmap)
            }
        }

        val adapter = PassportDataPagerAdapter(this, passData)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "Liveness"
                }
                1 -> {
                    tab.text = "Person"
                }
                2 -> {
                    tab.text = "Address"
                }
            }
        }.attach()
        if ((livenessScore > 0.79 && similarityScore > 0.46)) {
            binding.goMainBtn.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    val action =
                        BasePassportTabFragmentDirections.actionBasePassportTabFragmentToMainFragment()
                    findNavController().navigate(action)
                }
            }
        } else {
            binding.goMainBtn.visibility = View.GONE
        }
    }

    override fun subscribeToLiveData() {
    }


}*/
