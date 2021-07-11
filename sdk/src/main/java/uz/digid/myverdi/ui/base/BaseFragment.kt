package uz.digid.myverdi.ui.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import uz.digid.myverdi.R
import uz.digid.myverdi.utils.SingleBlock
import uz.digid.myverdi.utils.createFactory
import uz.digid.myverdi.utils.util.NetworkManager
import uz.digid.myverdi.utils.util.NetworkUtils


abstract class BaseFragment<V : ViewDataBinding, M : ViewModel> : Fragment() {


    protected lateinit var binding: V

    protected lateinit var mViewModel: M

    protected abstract fun createViewModel(): M
    abstract val layoutId: Int
    protected fun onBackPressed(block: SingleBlock<Unit>) {
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    block.invoke(Unit)
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel()
        context?.let { NetworkManager.instance.register(it) }
    }

    abstract fun initUI()

    abstract fun subscribeToLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = mViewModel.createFactory()
        ViewModelProvider(this, viewModelFactory).get(mViewModel.javaClass)
        subscribeToLiveData()
        checkNetwork()
        NetworkManager.instance.networkStateEvent.observe(viewLifecycleOwner, {
            checkNetwork()
        })

    }

    protected fun hideKeyboard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun checkNetwork() {
        if (!NetworkUtils.isNetworkAvailable(requireContext())) {
            Snackbar.make(requireContext(),binding.root, getString(R.string.waiting_network),Snackbar.LENGTH_LONG).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        NetworkManager.instance.unregister()
        //Welcome message popup appears onConfigurationChange(change darkMode theme)
        arguments?.clear()
    }

}