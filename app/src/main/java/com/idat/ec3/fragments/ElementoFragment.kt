package com.idat.ec3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idat.ec3.R
import com.idat.ec3.RVpunkapiListAdapter
import com.idat.ec3.databinding.FragmentElementoBinding


class ElementoFragment : Fragment(R.layout.fragment_elemento) {

    private lateinit var binding: FragmentElementoBinding
    private lateinit var viewModel: PunkapiListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentElementoBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(PunkapiListViewModel::class.java)

        val adapter = RVpunkapiListAdapter(emptyList()) { punkApi ->
            val couponDetailDirection = ElementoFragmentDirections.actionElementoFragmentToElementosDetailFragment(punkApi)
            findNavController().navigate(couponDetailDirection)
        }

        binding.rvPunkapiList.adapter = adapter
        binding.rvPunkapiList.layoutManager = GridLayoutManager(
            requireContext(), 2,
            RecyclerView.VERTICAL, false
        )

        viewModel.punkApiResponse.observe(viewLifecycleOwner) { punkApiList ->
            adapter.updateData(punkApiList)
        }
        viewModel.getCouponsFromService()
    }
}