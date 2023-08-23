package com.idat.ec3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.idat.ec3.databinding.ActivityAddPunkBinding

class AddPunkActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddPunkBinding
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_punk)
        binding = ActivityAddPunkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = Firebase.firestore

        binding.btnRegisterPunkapi.setOnClickListener{
            val name: String = binding.tilPunkName.editText?.text.toString()
            val tagline :String=binding.tilPunkTagline.editText?.text.toString()
            val first_brewed:String = binding.tilPunkFirstBrewed.editText?.text.toString()

            if (name.isNotEmpty() && tagline.isNotEmpty() && first_brewed.isNotEmpty()){
                val punkapi= hashMapOf(
                    "name" to name,
                    "tagline" to tagline,
                    "first_brewed" to first_brewed
                )
                firestore.collection("punkapi")
                    .add(punkapi)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Agregado correctamente con id: ${it.id}", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"No se agregoel elemento", Toast.LENGTH_SHORT).show()
                    }
            }
        }

    }

    /*private fun getData(){
        firestore.collection("punkapi").get()
            .addOnSuccessListener {
                val punks = mutableListOf<PunkApiFirebase>()
                for(document in it.documents){
                    val punkApi = PunkApiFirebase(
                        name = document.data?.get("name").toString(),
                        first_brewed = document.data?.get("first_brewed").toString(),"",""
                    )
                    punks.add(punkApi)
                }
            }
    }*/
    fun getData(callback: (List<PunkApiFirebase>) -> Unit) {
        firestore.collection("punkapi").get()
            .addOnSuccessListener { querySnapshot ->
                val punkApiList = mutableListOf<PunkApiFirebase>()

                for (document in querySnapshot.documents) {
                    val name = document.getString("name") ?: ""
                    val firstBrewed = document.getString("first_brewed") ?: ""
                    val tagline = document.getString("tagline") ?: ""

                    val punkApi = PunkApiFirebase(name, firstBrewed, tagline)
                    punkApiList.add(punkApi)
                }

                callback(punkApiList)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al obtener datos", Toast.LENGTH_SHORT).show()
            }
    }
}