package com.pmdm.virgen.dogapi.ui.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santi.pmdm.virgen.dogapicleanarchitecture.databinding.ItemDogBinding
import com.santi.pmdm.virgen.dogapicleanarchitecture.domain.models.Dog


//MODIFICADO onDelete
//Se encargará de inicializar las vistas, elemento por elemento. Pondrá todos los datos que le pasa el adaptador.

class ViewHDog(view: View, private val onDelete: (Int) ->Unit) : RecyclerView.ViewHolder(view){
    private val binding: ItemDogBinding

    init {
        binding = ItemDogBinding.bind(view)
    }


    fun rendereize(get: Dog, position: Int) {
        Glide
            .with(itemView.context)
            .load(get.image)
            .centerCrop()
            .into(binding.ivImagen)

        //MODIFICADO
        binding.btnDelete.setOnClickListener {
            onDelete(get.id)
        }

    }

}
