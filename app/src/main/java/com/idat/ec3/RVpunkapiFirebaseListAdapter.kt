package com.idat.ec3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Entity
import androidx.room.Ignore
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.idat.ec3.databinding.ItemPunkapiBinding

class RVpunkapiFirebaseListAdapter(
    private var punkApiFirebaseList: List<PunkApiFirebase>
) : RecyclerView.Adapter<PunkApiFirebaseVH>() {

    fun updateData(newData: List<PunkApiFirebase>) {
        punkApiFirebaseList = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PunkApiFirebaseVH {
        val binding = ItemPunkapiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PunkApiFirebaseVH(binding)
    }

    override fun getItemCount(): Int = punkApiFirebaseList.size

    override fun onBindViewHolder(holder: PunkApiFirebaseVH, position: Int) {
        val punkApiFirebase = punkApiFirebaseList[position]
        holder.bind(punkApiFirebase)
    }
}
class PunkApiFirebaseVH(private val binding: ItemPunkapiBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(punkApiFirebase: PunkApiFirebase) {
        binding.txtName.text = punkApiFirebase.name
        binding.txtEslogan.text = punkApiFirebase.tagline
        binding.txtPrimerae.text = punkApiFirebase.first_brewed
    }
}