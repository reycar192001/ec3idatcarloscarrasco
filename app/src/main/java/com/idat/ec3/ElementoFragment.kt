package com.idat.ec3

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idat.ec3.databinding.FragmentElementoBinding


class ElementoFragment : Fragment() {

    private lateinit var binding: FragmentElementoBinding
    private lateinit var viewModel: PunkapiListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PunkapiListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentElementoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVpunkapiListAdapter(listOf())
        binding.rvPunkapiList.adapter = adapter
        binding.rvPunkapiList.layoutManager = GridLayoutManager(requireContext(),2,
            RecyclerView.VERTICAL,false)
        viewModel.punkapiList.observe(requireActivity()) {
            adapter.elementos = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getCouponsFromService()

    }


}