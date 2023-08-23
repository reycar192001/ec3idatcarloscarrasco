package com.idat.ec3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.idat.ec3.databinding.FragmentFavoritoBinding
import com.idat.ec3.databinding.FragmentInformacionBinding


class InformacionFragment : Fragment() {

    private lateinit var binding: FragmentInformacionBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: RVpunkapiFirebaseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformacionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firestore = Firebase.firestore

        // Inicializa el adaptador con una lista vacía
        adapter = RVpunkapiFirebaseListAdapter(emptyList())
        binding.rvPunkapiListfirebase.adapter = adapter

        // Configura un GridLayoutManager para el RecyclerView
        binding.rvPunkapiListfirebase.layoutManager = GridLayoutManager(
            requireContext(),
            2, // Número de columnas en el grid
            RecyclerView.VERTICAL,
            false
        )


        // Llama a la función para cargar los datos desde Firebase
        loadFirebaseData()
    }

    private fun loadFirebaseData() {
        (activity as? AddPunkActivity)?.getData { punkApiFirebaseList ->
            // Actualiza los datos en el adaptador
            adapter.updateData(punkApiFirebaseList)
        }
    }
}