/*
package uz.digid.myverdi.ui.passportInfo.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import uz.digid.myverdi.R
import uz.digid.myverdi.databinding.FragmentInfoPersonBinding
import uz.digid.myverdi.ui.adapters.PersonAdapter
import uz.digid.myverdi.ui.adapters.PersonLivenessAdapter
import uz.digid.myverdi.ui.adapters.PersonPhotoAdapter

class PersonItemPagerFragment(private val list: List<Pair<String, String>>, private val pos: Int) : Fragment() {
    private lateinit var binding: FragmentInfoPersonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info_person, container, false)
        setData(list, pos)
        return binding.root

    }

    private fun setData(list: List<Pair<String, String>>, pos: Int) {

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter =
                when (pos) {
                    0-> {
                        PersonPhotoAdapter(requireContext(), list)
                    }
                    1, 2 -> {
                        PersonAdapter(requireContext(), list)

                    }
                    else -> {
                        PersonLivenessAdapter(requireContext(), list)

                    }
                }
        }
    }

}*/
