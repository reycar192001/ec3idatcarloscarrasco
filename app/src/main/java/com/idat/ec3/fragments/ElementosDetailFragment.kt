package com.idat.ec3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.idat.ec3.PunkApi
import com.idat.ec3.databinding.FragmentElementosDetailBinding


class ElementosDetailFragment : Fragment() {

    private lateinit var binding: FragmentElementosDetailBinding
    val args: ElementosDetailFragmentArgs by navArgs()
    private lateinit var punkapi: PunkApi
    private lateinit var viewModel: ElementosDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        punkapi = args.punkapi
        viewModel = ViewModelProvider(requireActivity()).get(ElementosDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentElementosDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(punkapi.image_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.img)

        binding.txtName.text = punkapi.name
        binding.txtDetail.text = punkapi.description
        binding.txtAbv.text = punkapi.abv.toString()
        binding.btnAddFavorite.apply{
            visibility= if(punkapi.isFavorite) View.GONE else View.VISIBLE
        }
        binding.btnAddFavorite.setOnClickListener{
            punkapi.isFavorite=true
            viewModel.addFavorite(punkapi)
        }
    }

}