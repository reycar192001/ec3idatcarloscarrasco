package com.idat.ec3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idat.ec3.databinding.FragmentFavoritoBinding
import com.idat.ec3.databinding.FragmentInformacionBinding


class InformacionFragment : Fragment() {

    private lateinit var binding: FragmentInformacionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInformacionBinding.inflate(inflater, container, false)
        return binding.root
    }


}