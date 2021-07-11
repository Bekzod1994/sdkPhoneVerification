/*
package uz.digid.myverdi.ui.publicOffer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.digid.myverdi.R
import uz.digid.myverdi.databinding.FragmentPublicOfferBinding
import uz.digid.myverdi.utils.constants.AppConstants


class PublicOfferFragment : Fragment() {
    private lateinit var binding: FragmentPublicOfferBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_public_offer, container, false)

        binding.wepView.apply {
            webViewClient = WebViewClient()
            loadUrl(AppConstants.PUBLIC_OFFER)
            settings.javaScriptEnabled = true
        }

        binding.buttonNotAccept.setOnClickListener {
            findNavController().popBackStack(R.id.splashFragment, true)
        }
        calculateProgression(binding.wepView)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val webView = binding.wepView
                    if (webView.canGoBack()) {
                        webView.goBack()
                    } else {
                        findNavController().popBackStack()
                    }
                }
            })
    }

    private fun calculateProgression(content: WebView): Float {
        val positionTopView = content.top.toFloat()
        val contentHeight = content.contentHeight.toFloat()
        val currentScrollPosition = content.scrollY.toFloat()
        return (currentScrollPosition - positionTopView) / contentHeight
    }

}*/
