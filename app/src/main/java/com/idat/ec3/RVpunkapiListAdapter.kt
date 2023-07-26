package com.idat.ec3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idat.ec3.databinding.ItemPunkapiBinding

class RVpunkapiListAdapter(var elementos:List<PunkApi>): RecyclerView.Adapter<PunkApiVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PunkApiVH {
        val binding =ItemPunkapiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PunkApiVH(binding)
    }

    override fun getItemCount(): Int =elementos.size

    override fun onBindViewHolder(holder: PunkApiVH, position: Int) {
        holder.bind(elementos[position])
    }
}

class PunkApiVH(private val binding: ItemPunkapiBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(elementos: PunkApi) {
        binding.imageView.setImageResource(R.drawable.flame)
        binding.txtName.text = elementos.name
        binding.txtEslogan.text = elementos.tagline
        binding.txtPrimerae.text = elementos.first_brewed
    }


}