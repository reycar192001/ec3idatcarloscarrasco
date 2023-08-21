package com.idat.ec3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.idat.ec3.databinding.ItemPunkapiBinding

class RVpunkapiListAdapter(var punkApiList:List<Beer>): RecyclerView.Adapter<PunkApiVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PunkApiVH {
        val binding =ItemPunkapiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PunkApiVH(binding)
    }

    override fun getItemCount(): Int =punkApiList.size

    override fun onBindViewHolder(holder: PunkApiVH, position: Int) {
        holder.bind(punkApiList[position])
    }
}

class PunkApiVH(private val binding: ItemPunkapiBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(punkApi: Beer) {
        Glide.with(itemView)
            .load(punkApi.image_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageView)
        binding.imageView.setImageResource(R.drawable.flame)
        binding.txtName.text = punkApi.name
        binding.txtEslogan.text = punkApi.tagline
        binding.txtPrimerae.text = punkApi.first_brewed
    }
}