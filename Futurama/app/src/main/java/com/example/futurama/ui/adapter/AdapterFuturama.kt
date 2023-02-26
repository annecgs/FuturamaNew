package com.example.futurama.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futurama.data.dto.FuturamaCharactersItem
import com.example.futurama.databinding.BoxItemListBinding

class AdapterFuturama: ListAdapter<FuturamaCharactersItem, AdapterFuturama.ViewHolder>(DIFF_CALLBACK.DIFF_CALLBACK) {

    var onClickListener: ((peopleId: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BoxItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: BoxItemListBinding,
        private val onClickListener: ((peopleId: String) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(x: FuturamaCharactersItem) {
            setImage(x)
            setName(x)
            setGender(x)
            setSpecies(x)
            setHomePlanet(x)
            setOcupation(x)

            binding.root.setOnClickListener {
                onClickListener?.invoke(x.name.first.toString())
            }
        }

        private fun setName(x: FuturamaCharactersItem){
            if (!x.name.first.isNullOrEmpty() && !x.name.middle.isNullOrEmpty() && !x.name.last.isNullOrEmpty()){
                binding.nameItem.text = x.name.first.toString() + " " + x.name.middle.toString() + " " + x.name.last.toString()
            } else if(!x.name.first.isNullOrEmpty() && x.name.middle.isNullOrEmpty() && !x.name.last.isNullOrEmpty()){
                binding.nameItem.text = x.name.first.toString() + " " + x.name.last.toString()
            }else {
                binding.nameItem.text = x.name.first.toString()
            }
        }

        private fun setGender(x: FuturamaCharactersItem){
            if(!x.gender.isNullOrEmpty()){
                binding.genderItem.text = "Gender: "+x.gender.toString()
            } else{
                binding.genderItem.text = "Gender: Not Found"
            }
        }


        private fun setSpecies(x: FuturamaCharactersItem){
            if(!x.species.isNullOrEmpty()){
                binding.speciesItem.text = "Specie: "+x.species.toString()
            } else{
                binding.speciesItem.text = "Specie: Not Found"
            }
        }


        private fun setHomePlanet(x: FuturamaCharactersItem){
            if(!x.homePlanet.isNullOrEmpty()){
                binding.homePlanetItem.text = "HomePlanet: "+x.homePlanet.toString()
            } else{
                binding.homePlanetItem.text = "HomePlanet: Not Found"
            }
        }

        private fun setOcupation(x:FuturamaCharactersItem){
            if(!x.occupation.isNullOrEmpty()){
                binding.ocupationItem.text = "Ocupation: "+x.occupation.toString()
            } else{
                binding.ocupationItem.text = "Ocupation: Not Found"
            }
        }

        private fun setImage(x: FuturamaCharactersItem) {
            if (!x.images.main.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(x.images.main)
                    .into(binding.imageItem)
            } /*else {
                Glide.with(binding.root.context)
                    .load(R.drawable.bruxonaoidentificado)
                    .into(binding.imagePeople)
            }*/
        }

       /* private fun getFavorite(x: PersonagensItem) {
            when (x.isFavorite) {
                true -> binding.ivFavoriteItem.visibility = View.VISIBLE
                false -> binding.ivFavoriteItem.visibility = View.GONE
            }
        }*/
    }

    companion object DIFF_CALLBACK {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FuturamaCharactersItem>() {
            override fun areItemsTheSame(oldItem: FuturamaCharactersItem, newItem: FuturamaCharactersItem): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: FuturamaCharactersItem, newItem: FuturamaCharactersItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}