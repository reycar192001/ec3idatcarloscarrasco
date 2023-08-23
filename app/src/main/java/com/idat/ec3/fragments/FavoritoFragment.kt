package com.idat.ec3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idat.ec3.RVpunkapiListAdapter
import com.idat.ec3.databinding.FragmentFavoritoBinding


class FavoritoFragment : Fragment() {

    private lateinit var binding: FragmentFavoritoBinding
    private lateinit var viewModel: FavoritoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(FavoritoViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVpunkapiListAdapter(listOf()){ coupon->
            val punkDetailDirection = FavoritoFragmentDirections.actionFavoritoFragmentToElementosDetailFragment(coupon)
            findNavController().navigate(punkDetailDirection)

        }

        binding.rvPunkList.adapter = adapter
        binding.rvPunkList.layoutManager = GridLayoutManager(requireContext(),2,
            RecyclerView.VERTICAL,false)
        viewModel.favorites.observe(requireActivity()) {
            adapter.punkApiList = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavorites()
    }
}