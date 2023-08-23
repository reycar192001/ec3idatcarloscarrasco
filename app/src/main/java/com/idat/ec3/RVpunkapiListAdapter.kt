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

class RVpunkapiListAdapter(
    var punkApiList: List<PunkApi>,
    private val onItemClick: (PunkApi) -> Unit
) : RecyclerView.Adapter<PunkApiVH>() {

    fun updateData(newData: List<PunkApi>) {
        punkApiList = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PunkApiVH {
        val binding = ItemPunkapiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PunkApiVH(binding)
    }

    override fun getItemCount(): Int = punkApiList.size

    override fun onBindViewHolder(holder: PunkApiVH, position: Int) {
        val beer = punkApiList[position]
        holder.bind(beer)

        // Asigna el clic en el elemento
        holder.itemView.setOnClickListener {
            onItemClick(beer)
        }
    }
}
class PunkApiVH(private val binding: ItemPunkapiBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(punkApi: PunkApi) {
        Glide.with(itemView)
            .load(punkApi.image_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageView)
        binding.imageView.setImageResource(R.drawable.ic_google)
        binding.txtName.text = punkApi.name
        binding.txtEslogan.text = punkApi.tagline
        binding.txtPrimerae.text = punkApi.first_brewed
    }
}