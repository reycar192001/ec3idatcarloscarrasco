package com.idat.ec3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idat.ec3.databinding.ActivityMenuBinding
import com.idat.ec3.databinding.FragmentElementosDetailBinding
import com.idat.ec3.databinding.FragmentInformacionBinding


class ElementosDetailFragment : Fragment() {

    private lateinit var binding: FragmentElementosDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentElementosDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


}