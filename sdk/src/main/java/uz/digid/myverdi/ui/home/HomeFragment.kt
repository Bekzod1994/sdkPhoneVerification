/*
package uz.digid.myverdi.ui.home

import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.findNavController
import uz.digid.myverdi.R
import uz.digid.myverdi.data.model.PersonDetails
import uz.digid.myverdi.databinding.FragmentHomeBinding
import uz.digid.myverdi.di.Injection
import uz.digid.myverdi.ui.base.BaseFragment
import uz.digid.myverdi.ui.dialog.ConfirmDialog
import uz.digid.myverdi.ui.dialog.ConfirmDialogListener


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutId = R.layout.fragment_home

    override fun createViewModel(): HomeViewModel {
        return HomeViewModel(
            Injection.providerRepository(),
            Injection.providerSchedulerProvider()
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initUI() {
        binding.toolbar.inflateMenu(R.menu.exit_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.exit -> {
                    logout()
                    true
                }
                else -> false
            }
        }
        onBackPressed {
            requireActivity().finish()
        }
        binding.identificationButton.setOnClickListener {
            val action = HomeFragmentDirections.actionAuthFragmentToIdentificationFragment(
                PersonDetails()
            )
            requireActivity().findNavController(R.id.nav_host_fragment).navigate(action)

        }
        binding.authorizationButton.setOnClickListener {
            val action = HomeFragmentDirections.actionAuthFragmentToSelfieFragment(
                PersonDetails()
            )
            requireActivity().findNavController(R.id.nav_host_fragment).navigate(action)
        }
    }

    override fun subscribeToLiveData() {

    }
    private fun logout() {
        ConfirmDialog(
            requireContext(),
            getString(R.string.log_out),
            R.string.no,
            R.string.yes,
            object : ConfirmDialogListener {
                override fun onClickPositiveButton() {
                    hideKeyboard()
                    mViewModel.deleteUser()
                }

                override fun onClickNegativeButton() {}
            }).show()
    }
}*/
